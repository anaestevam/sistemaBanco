package udp;

import java.net.DatagramPacket;
import java.net.SocketException;

import servico.Caixa;

public class DatabaseBackupUDP {
	private ServidorUDP udp; 
	
	public DatabaseBackupUDP(Integer porta) throws NumberFormatException, SocketException {
		udp = new ServidorUDP(porta);
	}

	public void iniciar() throws Exception {
		System.out.println("UDP: Banco Backup inicializado!");
		while (true) {
			DatagramPacket pacote = udp.lerMensagem();
			String mensagem = new String(pacote.getData());
			
			if(!udp.servicoOn(8084)) {				
				Caixa caixa = new Caixa();
				caixa.iniciarConta(mensagem);
				System.out.println("UDP: Mesagem chegou ao banco pelo backup!");
			}		
		}
	}
}