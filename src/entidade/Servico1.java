package entidade;

import udp.ServicoUDP;
import tcp.ServicoTCP;

public class Servico1 {
	public static void main(String[] args) throws Exception {
		// UDP
		/*ServicoUDP servicoUDP = new ServicoUDP(8082);
		servicoUDP.iniciar();
		System.out.println("UDP: Serviço A desligando...");
		*/
		// TCP
		
		 ServicoTCP servicoTCP = new ServicoTCP(8082);
		servicoTCP.iniciar();
		System.out.println("TCP: Seviço A desligando...");
		
	}
}
