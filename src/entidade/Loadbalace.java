package entidade;

import udp.LoadBalaceUDP;
import udp.ServidorUDP;

public class Loadbalace {
	
	public static void main(String[] args) throws Exception {
	
		LoadBalaceUDP loadBalaceUDP = new LoadBalaceUDP(args[0]);
		loadBalaceUDP.iniciar();
		System.out.println("UDP: Load Balace desligando...");
		

	}
}
