package loadbalace;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Reserva {
	
	public static Integer posicao = 0;
	
	public static Integer getPorta() {
		Set<Integer> portas = Ips.servicoIpMap.keySet();
        List<Integer> listaPortas = new ArrayList<>();
        listaPortas.addAll(portas);
        Integer resultado = null;

       /* synchronized (posicao) {
            if (posicao > listaPortas.size() - 1) {
                posicao = 0;
            }
            resultado = listaPortas.get(posicao); 
            posicao++;
        }*/
        return resultado;
	}
}
