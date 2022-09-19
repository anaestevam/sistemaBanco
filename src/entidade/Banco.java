package entidade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;
import java.util.StringTokenizer;

public class Banco {
	
	private int id;
	private Conta conta;
	private int containserida;
	private double valor;
	private String operacao = null;
	private Banco banco;
	
	public int getId() {
        return id;
    }

	public void setId() {
        this.id++;
	}
	
	public int getContainserida() {
		return containserida;
	}

	public void setContainserida(int containserida) {
		this.containserida = containserida;
	}

	public Banco() {
		
	}
	
	public Banco(Conta conta, double valor) {
		super();
		this.valor = valor;
		this.conta = conta;
		conta.depositar(conta, valor);
	}
	
	public Banco(Conta conta) {
		super();
		this.conta = conta;
		conta.getSaldo();
	}
	
	public void retornarConta(int containserida) {
		if(containserida == conta.getConta()) {
			System.out.println(conta);
		}else {
			System.out.println("Conta nao encontrada!");
		}
	}
	

	public Banco(Integer porta) throws NumberFormatException, IOException  {
		banco = new Banco();
		try {
			DatagramSocket serversocket = new DatagramSocket (porta);
			while(true) {
				byte[] receivemessage = new byte[1024];
				DatagramPacket receivepacket = new DatagramPacket(receivemessage, receivemessage.length);
				serversocket.receive(receivepacket);
				String message = new String(receivepacket.getData());
				StringTokenizer tokenizer = new StringTokenizer(message, ",");
				while(tokenizer.hasMoreElements()) {
					operacao = tokenizer.nextToken();
					containserida = Integer.parseInt(tokenizer.nextToken());
					valor = Float.parseFloat(tokenizer.nextToken());
				}
				switch(operacao) {
				case "Saldo":
					conta.getSaldo();
					break;
				case "Depositar":
					conta.depositar(conta, valor);
					break;
				case "Sacar":
					conta.sacar(conta, valor);
					break;
				}
				System.out.printf("Operacao realizada:"+ operacao +"-"+ conta +"-"+ receivepacket.getAddress());
				String reply = "Confirmo recebimento de:"+ message;
				byte[] replymsg = reply.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(replymsg, replymsg.length, receivepacket.getAddress(), receivepacket.getPort());
				serversocket.send(sendPacket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}

