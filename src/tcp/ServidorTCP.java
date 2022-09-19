package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import servico.Caixa;

public class ServidorTCP implements Runnable {
	private Socket cliente = null;
	private Socket servico = null;
	private Integer porta;
	private Boolean database;
	private Boolean temServico;
	
	public ServidorTCP() {
    
    }
	
    public ServidorTCP(Socket cliente, Integer porta, Boolean database) throws IOException {
        this.cliente = cliente;
        this.porta = porta;
        this.database = database;
        
        if(porta != null) {
        	this.servico = new Socket(InetAddress.getLocalHost(), porta);
        	this.temServico = true;
        } else
        	this.temServico = false;        
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    public void run() {
        BufferedReader clienteBr = null;
        PrintWriter clienteOut = null;
        
        BufferedReader servicoBr = null;
        PrintWriter servicoOut = null;        

        System.out.println("TCP: Nova conexão em " + this.cliente.getInetAddress() + ":" + this.cliente.getPort());
        try {
        	String mensagem = null;
        	if(servico == null) {
        		System.out.println("TCP: Nenhum servidor disponivel!");
        		mensagem = "Nenhum servidor disponivel";
        	}
        	else {        		
        		clienteBr = getReader(cliente);
        	}
            
        	mensagem = clienteBr.readLine();
            System.out.println("TCP: Lendo mensagem " + mensagem + "\n do endereço " + cliente.getPort() + "\n");
            
            if(this.porta == 8082 || this.porta == 8083) {
            	clienteOut = new PrintWriter(cliente.getOutputStream());
            	clienteOut.write("Enviando mensagem de confirmacao!");            	
            	clienteOut.flush();
            }
            
            if(this.temServico) {
            	 servicoBr = getReader(servico);
                 
                 servicoOut = new PrintWriter(servico.getOutputStream());
                 servicoOut.write(mensagem);
                 servicoOut.flush();
                 
                 System.out.println("TCP: Enviando mensagem " + mensagem + "\n do endereço " + servico.getPort() + "\n");
            }

            if(mensagem != null && database) {
            	Caixa caixa = new Caixa();
            	caixa.iniciarConta(mensagem);
            	System.out.println("TCP: Mensagem chegou ao banco de dados!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(clienteBr != null)
                	clienteBr.close();
                if(clienteOut != null)
                	clienteOut.close();
                
                if(servicoBr != null)
                	servicoBr.close();
                if(servicoOut != null)
                	servicoOut.close();
                
                if(cliente != null)
                	cliente.close();
                if(servico != null) {
                	servico.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Socket conectar(int porta) {
    	Socket socket = null;
    	try {
			socket = new Socket(InetAddress.getLocalHost(), porta);
		} catch (IOException e) {
			System.out.println("TCP: Conexao com servidor falhou!");
		}
    	return socket;
    }
}