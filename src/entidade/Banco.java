package entidade;

import java.util.List;

public class Banco {
	
	private List<Conta> contas;
	private List<Banco> bancos;
	
	public Banco() {
		/* Fazer construtor */
	}
	
	public List<Conta> getContas() {
		return contas;
	}
	
	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

}

