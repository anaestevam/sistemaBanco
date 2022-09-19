package http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHTTP {

	public ServidorHTTP() {

		System.out.println("Meu Servidor da Web iniciado");

		try (ServerSocket serverSocket = new ServerSocket(9000)) {

			while (true) {

				System.out.println("Aguardando solicitação do cliente"+serverSocket.getInetAddress());

				Socket remote = serverSocket.accept();

				System.out.println("Conexao feita");

				new Thread(new ClientHandler(remote)).start();

			}

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}

	public static void main(String args[]) {

		new ServidorHTTP();

	}
}
