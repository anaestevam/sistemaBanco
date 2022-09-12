package udp;

import java.net.DatagramPacket;
import java.net.SocketException;

public class LoadBalaceUDP {

		public ServidorUDP udp; 
		public LoadBalaceUDP(String porta) throws NumberFormatException, SocketException {
			udp = new ServidorUDP(porta);
		}

		public void iniciar() throws Exception {
			System.out.println("UDP: Load Balace inicializado!");
			while (true) {
				DatagramPacket pacote = udp.lerMensagem();
				String mensagem = new String(pacote.getData());
				udp.enviarMensagemJMeter("Enviando mensagem de confirmação!", Integer.valueOf(pacote.getPort()), pacote.getAddress());
				
			}
			
			
		}
}