package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import loadbalace.Reserva;

public class LoadBalaceTCP {
	private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 20;
    ServidorTCP tcp;

    public LoadBalaceTCP(Integer porta) throws IOException {
        serverSocket = new ServerSocket(porta);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() * POOL_SIZE);
        tcp = new ServidorTCP();
    }

    public void iniciar() {
    	System.out.println("TCP: LoadBalancer inicializado!");
        while(true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                Integer portaServico = Reserva.getPorta(); 
                Socket socketEnviar = tcp.conectar(portaServico);
                if(socketEnviar == null) {
                	portaServico = Reserva.getPorta(); 
                }
                executorService.execute(new ServidorTCP(socket, portaServico, false));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}