package udp;

import java.net.DatagramPacket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

public class Database {
	private List<Integer> backup;
	private ServidorUDP udp; 
		
	public Database(String porta, Integer backup) throws NumberFormatException, SocketException {
		udp = new ServidorUDP(porta);
		this.backup = Arrays.asList(backup);
	}

	public void iniciar() throws Exception {
		System.out.println("UDP: Database inicializado!");
		while (true) {
			DatagramPacket pacote = udp.lerMensagem();
			String mensagem = new String(pacote.getData());
			
			for(Integer porta : backup) {
				if(udp.servicoOn(porta)) {					
					udp.enviarMensagem(mensagem, porta);
				}
			}
			
			System.out.println("UDP: Mensagem chegou no Banco de Dados");
		}
	}
}