package loadbalace;

import java.util.HashMap;

public class Ips {

	public static HashMap<Integer, String> ipMap = new HashMap<>();
	public static HashMap<Integer, String> servicoIpMap = new HashMap<>();

    static {
    	 ipMap.put(8080, "127.0.0.1");		// JMeter
         ipMap.put(9081, "127.0.0.1");		// LoadBalanceUDP
         //ipMap.put(9082, "127.0.0.1");		// Banco
         ipMap.put(9083, "127.0.0.1");		// Database
         ipMap.put(8084, "127.0.0.1"); 
         ipMap.put(8085, "127.0.0.1"); 
    	servicoIpMap.put(8082, "127.0.0.1");	// Cliente1
    	servicoIpMap.put(8083, "127.0.0.1");	// Cliente2
    }

}
