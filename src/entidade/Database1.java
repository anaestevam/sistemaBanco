package entidade;

import udp.DatabaseUDP;
import tcp.DatabaseTCP;

public class Database1 {
	public static void main(String[] args) throws Exception {
//		 UDP
		/*
		DatabaseUDP bancoUDP = new DatabaseUDP(8084, 8085);
		bancoUDP.iniciar();
		System.out.println("UDP: Banco de Dados A desligando...");
		*/
		// TCP
		
		DatabaseTCP bancoTCP = new DatabaseTCP(8084);
		bancoTCP.iniciar();
		System.out.println("TCP: Banco de Dados A desligando...");
		
	}
}
