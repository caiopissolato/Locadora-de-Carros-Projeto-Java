public abstract class Pessoa {
	private String endereco;
	private int    telefone;
	private double divida;
	
	//Construtores
	public Pessoa(String endereco, int telefone) {
		this.endereco = endereco;
		this.telefone = telefone;
		this.divida   = 0.0;
	}
	
	public Pessoa(String endereco, int telefone, double divida) {
		this.endereco = endereco;
		this.telefone = telefone;
		this.divida   = divida;
	}
		
	public Pessoa() {
		this.endereco = "";
		this.telefone = 0;
		this.divida   = 0;
	}
		
	//Métodos acessors
	public String getEndereco() { return this.endereco; }
	public int    getTelefone() { return this.telefone; }
	public double getDivida()   { return this.divida;   }
		
	//Métodos mutators
	public void setEndereco(String endereco) { this.endereco = endereco; }
	public void setTelefone(int telefone)    { this.telefone = telefone; }
	public void setDivida(double divida)     { this.divida   = divida;   }
}