package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public interface DatabaseInterface {
	public int inserir(String[] listaDados) throws IOException;
	public double[] buscar(int id) throws FileNotFoundException;
	public Collection<String[]>  buscarPorTodos() throws FileNotFoundException;
	public int atualizar(int id, String[] listaDados) throws IOException;
	public void deletar(int id) throws IOException;
}
