package Udp;

import java.io.IOException;
import java.net.DatagramPacket;

public interface UDP {
    public DatagramPacket lerMensagem() throws IOException;

    public void enviarMensagem(String mensagem, Integer porta) throws Exception;
}
