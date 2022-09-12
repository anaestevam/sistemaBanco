package entidade;

import java.util.List;

public class Banco {
	
	private int codigo;
	private String nome;
	private double valor;
	private Conta conta;
	
	
	public Banco(int codigo, String nome, Conta conta, double valor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.conta = conta;
		conta.depositar(valor);
	}
	
	public Banco(int codigo, String nome, Conta conta) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.conta = conta;
		conta.getSaldo();
	}
	


}

