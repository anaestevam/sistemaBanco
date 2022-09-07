package loadbalace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ips {

	public static Map<Integer, String> ipMap = new ConcurrentHashMap<>();
	public static Map<Integer, String> servicoIpMap = new ConcurrentHashMap<>();

    static {
    	 ipMap.put(8080, "127.0.0.1");		// JMeter
         ipMap.put(8081, "127.0.0.1");		// Load Balance
    	//servicoIpMap.put(8082, "127.0.0.1");	// SamuAServer
    	//servicoIpMap.put(8083, "127.0.0.1");	// SamuBServer
        // ipMap.put(8084, "127.0.0.1");		// Database
        // ipMap.put(8085, "127.0.0.1"); 		// Database Backup
    }

}
