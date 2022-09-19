package entidade;

import tcp.LoadBalaceTCP;
import udp.LoadBalaceUDP;

public class Loadbalace {
	
	public static void main(String[] args) throws Exception {
	
		//UDP
		/*
		LoadBalaceUDP loadBalaceUDP = new LoadBalaceUDP(8081);
		loadBalaceUDP.iniciar();
		
		System.out.println("UDP: LoadBalance desligando...");
		*/
		// TCP
		
		LoadBalaceTCP loadBalaceTCP = new LoadBalaceTCP(9100);
		loadBalaceTCP.iniciar();
		System.out.println("TCP: LoadBalance desligando...");

	}
}
