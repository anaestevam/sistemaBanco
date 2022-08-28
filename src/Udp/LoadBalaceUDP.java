package Udp;

import java.net.DatagramPacket;
import java.net.SocketException;

public class LoadBalaceUDP {
    
    private ServidorUDP udp;
    public LoadBalaceUDP(Integer porta) throws NumberFormatException, SocketException {
        udp = new ServidorUDP(porta);
    }

    public void iniciar() throws Exception {
        System.out.println("UDP: Load Balace inicializado!");

    }
}
