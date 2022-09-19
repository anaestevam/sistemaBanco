package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;

import entidade.Banco;
import loadbalace.Reserva;

public class LoadBalaceUDP {

		public ServidorUDP udp; 
		public Banco banco;
		public LoadBalaceUDP(Integer porta) throws NumberFormatException, IOException {
			udp = new ServidorUDP(porta);
			//banco = new Banco(porta2);
		}
		public void iniciar() throws Exception {
			System.out.println("UDP: LoadBalance inicializado!");
			while (true) {
				DatagramPacket pacote = udp.lerMensagem();
				String mensagem = new String(pacote.getData());
				udp.enviarMensagemJMeter("Enviando mensagem de confirmacao!", Integer.valueOf(pacote.getPort()), pacote.getAddress());
				
				Integer portaServico = Reserva.getPorta(); 
				if(udp.servicoOn(portaServico)) {
					udp.enviarMensagem(mensagem, portaServico);
				} else {
					portaServico = Reserva.getPorta();
					udp.enviarMensagem(mensagem, portaServico);
				}
			/*	
				try {
					udp.enviarMensagem(mensagem, portaServico);
				} catch(Exception e) {
					portaServico = Reserva.getPorta();
					udp.enviarMensagem(mensagem, portaServico);
				}*/
			}
		}
}