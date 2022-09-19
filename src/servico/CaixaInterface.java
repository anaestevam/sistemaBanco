package servico;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CaixaInterface {
	public void iniciarConta(String dados) throws NumberFormatException, IOException ;
	public void chamarConta(String[] listaDados) throws FileNotFoundException;
	public void salvarDados(String[] listaDados) throws NumberFormatException, IOException;
}
