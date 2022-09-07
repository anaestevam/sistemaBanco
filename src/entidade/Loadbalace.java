package entidade;

import udp.LoadBalaceUDP;

public class Loadbalace {
	
	public static void main(String[] args) throws Exception {
	
		LoadBalaceUDP loadBalaceUDP = new LoadBalaceUDP(8081);
		loadBalaceUDP.iniciar();
		System.out.println("UDP: Load Balancer desligando...");
		
//		// TCP
//		TCPLoadBalancer loadBalancerTCP = new TCPLoadBalancer(8081);
//		loadBalancerTCP.iniciar();
//		System.out.println("TCP: Load Balancer desligando...");
	}
}
