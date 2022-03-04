public class PessoaJuridica extends Pessoa {
	private String    razao_social; // nome "oficial" da empresa
	private int       cnpj;
	private String    nome_fantasia; // nome conhecido popularmente da empresa
	private final int tipo = 2; // para identificar que é uma pessoa jurídica
	
	//Construtor
	public PessoaJuridica(String razao_social, int cnpj, String nome_fantasia, String endereco, int telefone) {
		super(endereco, telefone);
		this.razao_social  = razao_social;
		this.cnpj          = cnpj;
		this.nome_fantasia = nome_fantasia;
	}
	
	public PessoaJuridica(String razao_social, int cnpj, String nome_fantasia, String endereco, int telefone, double divida) {
		super(endereco, telefone, divida);
		this.razao_social  = razao_social;
		this.cnpj          = cnpj;
		this.nome_fantasia = nome_fantasia;
	}
	
	
	public PessoaJuridica() {
		super(" ", 0);
		this.razao_social  = " ";
		this.cnpj          = 0;
		this.nome_fantasia = " ";
	}
	
	//Métodos acessors
	public String getRazaoSocial()  { return this.razao_social; }
	public int    getCnpj()         { return this.cnpj; }
	public String getNomeFantasia() { return this.nome_fantasia; }
	public int    getTipo()         { return this.tipo; }
	
	//Métodos mutators
	public void setRazaoSocial(String razao_social)   { this.razao_social  = razao_social; }
	public void setCnpj(int cnpf)                     { this.cnpj          = cnpf; }
	public void setNomeFantasia(String nome_fantasia) { this.nome_fantasia = nome_fantasia; }
	
	//Método para imprimir a classe
	public void printPessoaJuridica() {
		if(this.getDivida() > 0)
			System.out.printf("Razao Social: %s \nCNPF: %d \nNome Fantasia: %s \nEndereco: %s \nTelefone: %d \nDivida: %.2f\n", this.getRazaoSocial(), this.getCnpj(), this.getNomeFantasia(), this.getEndereco(), this.getTelefone(), this.getDivida());
		else
			System.out.printf("Nome: %s \nCPF: %d \nNome Fantasia: %s \nEndereco: %s \nTelefone: %d \nCliente sem divida pendente\n", this.getRazaoSocial(), this.getCnpj(), this.getNomeFantasia(), this.getEndereco(), this.getTelefone());
	}
}