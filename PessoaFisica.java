public class PessoaFisica extends Pessoa {
	private String       nome;
	private int          cpf;
	private final int    tipo = 1; //para identificar que é uma pessoa física

	//Construtores
	public PessoaFisica(String nome, int cpf, String endereco, int telefone) {
		super(endereco, telefone);
		this.nome = nome;
		this.cpf  = cpf;
	}
	
	public PessoaFisica(String nome, int cpf, String endereco, int telefone, double divida) {
		super(endereco, telefone, divida);
		this.nome = nome;
		this.cpf  = cpf;
	}
	
	public PessoaFisica() {
		super(" ", 0);
		this.nome = " ";
		this.cpf  = 0;
	}
	
	//Métodos acessors
	public String getNome()    { return this.nome; }
	public int    getCpf()     { return this.cpf;  }
	public int    getTipo()    { return this.tipo; }
	
	//Métodos mutators
	public void setNome(String nome) { this.nome = nome; }
	public void setCpf(int cpf) 	 { this.cpf  = cpf;  }
	
	//Método para imprimir a classe
	public void printPessoaFisica() {
		if(this.getDivida() > 0)
			System.out.printf("Nome: %s \nCPF: %d \nEndereco: %s \nTelefone: %d \nDivida: %.2f\n", this.getNome(), this.getCpf(), this.getEndereco(), this.getTelefone(), this.getDivida());
		else
			System.out.printf("Nome: %s \nCPF: %d \nEndereco: %s \nTelefone: %d \nCliente sem divida pendente\n", this.getNome(), this.getCpf(), this.getEndereco(), this.getTelefone());
	}
}	