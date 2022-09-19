package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import loadbalace.Reserva;

public class DatabaseTCP  {
	private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 20;
    ServidorTCP tcp;

    public DatabaseTCP(Integer porta, Integer... backups) throws IOException {
        serverSocket = new ServerSocket(porta);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() * POOL_SIZE);
		tcp = new ServidorTCP();
    }

    public void iniciar() {
    	System.out.println("TCP: Banco de dados inicializado!");
        while(true) {
        	Socket socket = null;
            try {
                socket = serverSocket.accept();
                Integer portaBanco = 8085; 
                Socket socketEnviar = tcp.conectar(portaBanco);
                if(socketEnviar == null) {
                	portaBanco = null;
                }
                this.executorService.execute(new ServidorTCP(socket, portaBanco, true));
            } catch (Exception e) {
                e.printStackTrace();
            }
        	
        }
    }
}