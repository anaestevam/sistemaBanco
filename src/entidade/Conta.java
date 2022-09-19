package entidade;

import java.util.HashMap;

public class Conta {
	private HashMap<Integer,Float> contas = new HashMap<>();
	private int id;
    private int conta, saques;
    private double valor;
    private String nome;
    private String tipoConta;
	private double saldo;
	
	public Conta() {
	}
	
    public Conta(int conta, String tipoConta) {
		super();
		this.conta = conta;
		this.tipoConta = tipoConta;
	}

	public double getSaldo() {
        return saldo;
    }
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
    }
    
    public void sacar(Conta conta, double valor){
        if(saldo >= valor){
            saldo -= valor;
            saques++;
            System.out.println("Sacado: " + valor);
            System.out.println("Novo saldo: " + saldo + "\n");
        } else {
            System.out.println("Saldo insuficiente\n");
        }
    }
    

	public String toString() {
		return "Conta [conta=" 
				+ conta 
				+ ", nome=" 
				+ nome 
				+ ", tipoConta=" 
				+ tipoConta
				+ ", saldo=" 
				+ saldo + "]";
	}

	public void depositar(Conta conta, double valor){
        saldo += valor;
        System.out.println("Depositado: " + valor);
        System.out.println("Novo saldo: " + saldo + "\n");
    }
	
	 public int getId() {
	        return id;
	    }

	 public void setId(int id) {
	        this.id = id;
	 }
	    
    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
}
