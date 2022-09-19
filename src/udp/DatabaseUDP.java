package udp;

import java.net.DatagramPacket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

import entidade.Banco;
import servico.Caixa;

public class DatabaseUDP {
	private List<Integer> backup;
	private ServidorUDP udp; 
		
	public DatabaseUDP(Integer porta, Integer backup) throws NumberFormatException, SocketException {
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
			
			Caixa caixa = new Caixa();
			caixa.iniciarConta(new String(pacote.getData()));
			System.out.println("UDP: Mensagem chegou no Banco de Dados");
		}
	}
}