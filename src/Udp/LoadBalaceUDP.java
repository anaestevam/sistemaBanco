package Udp;

import java.net.DatagramPacket;
import java.net.SocketException;

import loadbalance.RoundRobin;

public class LoadBalaceUDP {
    private ServidorUDP udp;

    public LoadBalaceUDP(Integer porta) throws NumberFormatException, SocketException {
        udp = new ServidorUDP(porta);
    }

    public void iniciar() throws Exception {
        System.out.println("UDP: Load Balance inicializado!");
        while (true) {
            DatagramPacket pacote = udp.lerMensagem();
            String mensagem = new String(pacote.getData());
            udp.enviarMensagemJMeter("Enviando mensagem de confirmação!", Integer.valueOf(pacote.getPort()),
                    pacote.getAddress());

            Integer portaServico = RoundRobin.getPorta();
            if (udp.servicoOn(portaServico)) {
                udp.enviarMensagem(mensagem, portaServico);
            } else {
                portaServico = RoundRobin.getPorta();
                udp.enviarMensagem(mensagem, portaServico);
            }

            // try {
            // udp.enviarMensagem(mensagem, portaServico);
            // } catch(Exception e) {
            // portaServico = RoundRobin.getPorta();
            // udp.enviarMensagem(mensagem, portaServico);
            // }
        }
    }
}
