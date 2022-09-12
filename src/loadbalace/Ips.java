package loadbalace;

import java.util.HashMap;

public class Ips {

	public static HashMap<Integer, String> ipMap = new HashMap<>();
	public static HashMap<Integer, String> servicoIpMap = new HashMap<>();

    static {
    	 ipMap.put(8080, "127.0.0.1");		// JMeter
         ipMap.put(8081, "127.0.0.1");		// LoadBalaceUDP
    	servicoIpMap.put(8082, "127.0.0.1");	// Cliente1
    	//servicoIpMap.put(8083, "127.0.0.1");	// Cliente2
    }

}
