package udp;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import loadbalace.Ips;

public class ServidorUDP {
	public Integer porta;
	public DatagramSocket serverSocket;
	public List<Integer> listaPortas;
	public String ultimaPorta;
	
	public ServidorUDP(Integer porta) throws NumberFormatException, SocketException {
		this.porta = porta;
		this.serverSocket = new DatagramSocket(porta);
		
		Set<Integer> portas = Ips.ipMap.keySet();
		this.listaPortas = new ArrayList<Integer>(); 
		this.listaPortas.addAll(portas);
	}
	
	public DatagramPacket ler() throws IOException {
		byte[] mesagemRecebida = new byte[1024];
		DatagramPacket pacoteRecebido = new DatagramPacket(mesagemRecebida, mesagemRecebida.length);
		this.serverSocket.receive(pacoteRecebido);
		
		System.out.println("UDP: Lendo mensagem '" + new String(pacoteRecebido.getData()) + "' do endereço " + pacoteRecebido.getAddress() + ":" + pacoteRecebido.getPort() + "\n");
		return pacoteRecebido;
	}
	
	public void enviar(String mensagem, Integer porta) throws IOException {
		enviar(mensagem, porta, InetAddress.getLocalHost());
	}
	
	public void enviar(String mensagem, Integer porta, InetAddress endereco) throws IOException {
		byte[] bytesMensagem = mensagem.getBytes();
		DatagramPacket pacoteEnviar = new DatagramPacket(bytesMensagem, bytesMensagem.length, endereco, porta);
		System.out.println("UDP: Enviando mensagem '" + new String(pacoteEnviar.getData()) + "' para o endereço " + pacoteEnviar.getAddress() + ":" + pacoteEnviar.getPort() + "\n");
		
		this.serverSocket.send(pacoteEnviar);
	}
	
	public boolean servicoOn(Integer porta) {
		DatagramSocket sock = null;
		try {
			sock = new DatagramSocket(porta);
	        sock.close();
	        return false;
        } catch (BindException ignored) {
        	System.out.println("UDP: Ocupado!");
        	return true;
        } catch (SocketException ex) {
        	System.out.println(ex);
        	System.out.println("Timeout!");
        	return true;
        }
	}
	
	public void enviarMensagemJMeter(String mensagem, Integer porta, InetAddress endereco) throws IOException {
		byte[] bytesMensagem = mensagem.getBytes();
		DatagramPacket pacoteEnviar = new DatagramPacket(bytesMensagem, bytesMensagem.length, endereco, porta);
		System.out.println("UDP: Enviando mensagem '" + new String(pacoteEnviar.getData()) + "' para o endereco " + pacoteEnviar.getAddress() + ":" + pacoteEnviar.getPort() + "\n");
		
		this.serverSocket.send(pacoteEnviar);
	}
	
	public DatagramPacket lerMensagem() throws IOException {
		DatagramPacket pacote = ler();
		return pacote;
	}
	
	public void enviarMensagem(String mensagem, Integer porta) throws IOException {
		enviar(mensagem, porta);
	}
}