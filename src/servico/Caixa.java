package servico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import database.*;
import entidade.Banco;
import entidade.Conta;

public class Caixa implements CaixaInterface {	
	private static CaixaInterface instancia;
	protected DatabaseInterface database;
	
	public Caixa() {
		this.database = new Database();
	}
	

	public static CaixaInterface getInstancia() {
		if(instancia == null) {
			instancia = new Caixa();
		}
		return instancia;
	}
	

	public void iniciarConta(String dados) throws NumberFormatException, IOException {
		// id/conta/nome/TipoConta/saldo
		System.out.println(dados);
		String[] listaDados = dados.split("/");
		
		if(listaDados[1].equals("conta")) {
			chamarConta(listaDados);
		} else if(listaDados[1].equals("caixa")) {
			salvarDados(listaDados);
		}
	}
	
	public void chamarConta(String[] listaDados) throws FileNotFoundException {
		Banco banco = new Banco();
		banco.setId();
		banco.setContainserida(Integer.parseInt(listaDados[3]));
		
		Collection<String[]> bancoCompleto = this.database.buscarPorTodos();
		Conta conta = new Conta();
		
		if(bancoCompleto == null) {
			System.out.println("Caixa: Não existem conta cadastrada no momento.");
		} else {
			boolean contaEncontrada = false;
			for (String[] linha : bancoCompleto) {
				if(Integer.parseInt(linha[6]) == 1) {					
					
					conta.setConta(Integer.parseInt(listaDados[3]));
					conta.setNome(listaDados[4]);
					conta.setTipoConta(listaDados[5]);
					conta.setSaldo(Double.parseDouble(listaDados[6]));
					contaEncontrada = true;
				}
			}
		}
	}
	
	public void salvarDados(String[] listaDados) throws NumberFormatException, IOException {
		switch (listaDados[0]) {
		case "POST":
			this.database.inserir(listaDados);
			break;
		case "GET":
			this.database.buscar(Integer.parseInt(listaDados[2]));
			break;
		case "PUT":
			this.database.atualizar(Integer.parseInt(listaDados[2]), listaDados);
			break;
		case "DELETE":
			this.database.deletar(Integer.parseInt(listaDados[2]));
			break;
		default:
			break;
		}
	}


}
