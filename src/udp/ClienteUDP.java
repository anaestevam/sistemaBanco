package udp;

import java.net.*;
import java.util.*;
import java.io.*;

public class ClienteUDP {

	public static void main(String[] args) {

		/*
		 * System.out.println("Digite uma mensagem:");
		 * Scanner teclado = new Scanner(System.in);
		 * String mensagem = teclado.nextLine();
		 */
		DatagramSocket aSocket = null;

		try {
			aSocket = new DatagramSocket();
			byte[] msg = args[0].getBytes();
			InetAddress aHost = InetAddress.getByName(args[1]);
			int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(msg, msg.length, aHost, serverPort);
			aSocket.send(request);
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}

	}
}
