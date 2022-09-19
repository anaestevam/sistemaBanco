package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import entidade.Conta;

public class Database implements DatabaseInterface {
	private int newId = 0;
	private static final String FILENAME = "C:\\Users\\anaes\\Documents\\GitHub\\sistemaBanco\\src\\database.csv";
	
	public int inserir(String[] listaDados) throws IOException {
		Conta conta = new Conta();
		
		BufferedReader br = new BufferedReader(new FileReader(FILENAME));
		String linha;
		while((linha = br.readLine()) != null) {
			String[] dado = linha.split(",");
			
			if(dado[0] == "") {
				newId += 1;
			} else {
				newId = Integer.parseInt(dado[0])+1;
			}
		}		
		
		conta.setId(newId);
		
		try {
			FileWriter csv = new FileWriter(FILENAME, true);
			
			conta.setConta(Integer.parseInt(listaDados[3]));
			conta.setNome(listaDados[4]);
			conta.setTipoConta(listaDados[5]);
			conta.setSaldo(Double.parseDouble(listaDados[6]));
			
			csv.append(String.valueOf(conta.getId()) + ",");
			csv.append(String.valueOf(conta.getConta())+ ",");
			csv.append(conta.getNome() + ",");
			csv.append(conta.getTipoConta() + ",");
			csv.append(String.valueOf(conta.getSaldo()) + "\n");
			
			csv.flush();
			csv.close();
			System.out.println("BANCODEDADOS: Dados adicionados no banco! Id: " + conta.getId());
			
			return conta.getId();
		} catch (IOException e) {
			System.out.println("BANCODEDADOS: Problemas ao inserir dados no banco. Id: " + conta.getId());
			e.printStackTrace();
		}
		
		return -1;
	}


	public double[] buscar(int id) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(FILENAME));
		String row;

		try {			
			while ((row = br.readLine()) != null) {
				String[] data = row.split(",");
				if(data[0].equals(String.valueOf(id))) {
					System.out.println("BANCODEDADOS: Dado id=" + id + " encontrado!");
				}
			}

		} catch (IOException e) {
			System.out.println("BANCODEDADOS: Problemas ao buscar dado no banco. Id=" + id + " não está no banco!");
			e.printStackTrace();
		}
		
		return null;
	}
	

	public Collection<String[]> buscarPorTodos() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(FILENAME));
		Collection<String[]> dados = null;
		String row;

		try {			
			while ((row = br.readLine()) != null) {
				String[] data = row.split(",");
				dados.add(data);
			}
			
			return dados;
		} catch (IOException e) {
			System.out.println("BANCODEDADOS: Problemas ao buscar por todos!");
			e.printStackTrace();
		}
		
		return null;
	}


	public int atualizar(int id, String[] listaDados) throws IOException {
		deletar(id);
		
		Conta conta = new Conta();
		conta.setId(id);
			
		try {
			FileWriter csv = new FileWriter(FILENAME, true);
			
			conta.setConta(Integer.parseInt(listaDados[3]));
			conta.setNome(listaDados[4]);
			conta.setTipoConta(listaDados[5]);
			conta.setSaldo(Double.parseDouble(listaDados[6]));
			
			csv.append(String.valueOf(conta.getId()) + ",");
			csv.append(String.valueOf(conta.getConta())+ ",");
			csv.append(conta.getNome() + ",");
			csv.append(conta.getTipoConta() + ",");
			csv.append(String.valueOf(conta.getSaldo()) + "\n");
			
			csv.flush();
			csv.close();
			System.out.println("BANCODEDADOS: Dados atualizados no banco! Id: " + conta.getId());
			
			return conta.getId();
		} catch (IOException e) {
			System.out.println("BANCODEDADOS: Problemas ao atualizar dado no banco. Id: " + conta.getId());
			e.printStackTrace();
		}
		
		return -1;		
	}


	public void deletar(int id) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILENAME));
		Collection<String[]> dados = null;
		String row;

		try {			
			while ((row = br.readLine()) != null) {
				String[] data = row.split(",");
				System.out.println(data[0]);
				dados.add(data);
			}
			
			List<String[]> filtered = dados.stream()
					.filter(linha -> !linha[2].equals(id))
					.collect(Collectors.toList());
			
			FileWriter csv = new FileWriter(FILENAME, false);
			for (String[] linha : filtered) {
				for (String coluna : linha) {
					csv.append(coluna + ",");
				}
				csv.append("\n");
			}
			
			System.out.println("BANCODEDADOS: Dado id=" + id + " deletado com sucesso!");
			
			csv.flush();
			csv.close();
		} catch (IOException e) {
			System.out.println("BANCODEDADOS: Problemas ao deletar dado no banco. Id=" + id + " não está no banco!");
			e.printStackTrace();
		}
	}
}
