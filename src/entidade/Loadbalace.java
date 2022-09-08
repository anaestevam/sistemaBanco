package entidade;

import udp.LoadBalaceUDP;

public class Loadbalace {
	
	public static void main(String[] args) throws Exception {
	
		LoadBalaceUDP loadBalaceUDP = new LoadBalaceUDP(8081);
		loadBalaceUDP.iniciar();
		System.out.println("UDP: Load Balace desligando...");

	}
}
