package udp;
import java.io.IOException;
import java.net.DatagramPacket;

public interface Udp {
	
	public DatagramPacket lerMensagem() throws IOException;
	public void enviarMensagem(String mensagem, Integer porta) throws Exception;

}
