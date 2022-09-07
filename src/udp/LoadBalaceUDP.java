package udp;

import java.net.DatagramPacket;
import java.net.SocketException;

import loadbalace.Reserva;

public class LoadBalaceUDP {

		private ServidorUDP udp; 
		public LoadBalaceUDP(Integer porta) throws NumberFormatException, SocketException {
			udp = new ServidorUDP(porta);
		}

		public void iniciar() throws Exception {
			System.out.println("UDP: Load Balancer inicializado!");
			while (true) {
				DatagramPacket pacote = udp.lerMensagem();
				String mensagem = new String(pacote.getData());
				udp.enviarMensagemJMeter("Enviando mensagem de confirmação!", Integer.valueOf(pacote.getPort()), pacote.getAddress());

				Integer portaServico = Reserva.getPorta(); 
				if(udp.servicoOn(portaServico)) {
					udp.enviarMensagem(mensagem, portaServico);
				} else {
					portaServico = Reserva.getPorta();
					udp.enviarMensagem(mensagem, portaServico);
				}
				
				try {
					udp.enviarMensagem(mensagem, portaServico);
				} catch(Exception e) {
					portaServico = Reserva.getPorta();
					udp.enviarMensagem(mensagem, portaServico);
				}
			}
		}
}
