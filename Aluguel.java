import java.time.LocalDate;

public class Aluguel {
	private int        tipo_cliente;
	private LocalDate  data_ini;
	private int        id_cliente;
	private String     placa;
	private LocalDate  data_fim;
	private int        situacao;
	private double     valor_total;
	
	//Construtor
	public Aluguel(int dia, int mes, int ano, int id_cliente, String placa) {
		this.tipo_cliente = 0; // 1 para pessoa fisica 2 para pessoa juridica
		this.data_ini     = LocalDate.of(ano, mes, dia);
		this.id_cliente   = id_cliente;
		this.placa        = placa;
		this.data_fim     = LocalDate.of(1, 1, 1);
		this.situacao     = 1; //1 para situação ativa do aluguel, 2 para aluguel encerrado
		this.valor_total  = 0.0;

	}
	
	public Aluguel(int dia, int mes, int ano, int id_cliente, String placa, int situacao, double valor_total) {
		this.tipo_cliente = 0; // 1 para pessoa fisica 2 para pessoa juridica
		this.data_ini     = LocalDate.of(ano, mes, dia);
		this.id_cliente   = id_cliente;
		this.placa        = placa;
		this.data_fim     = LocalDate.of(1, 1, 1);
		this.situacao     = situacao;
		this.valor_total  = valor_total;

	}
	
	public Aluguel(int dia, int mes, int ano, int id_cliente, String placa, int situacao, double valor_total, int diaF, int mesF, int anoF) {
		this.tipo_cliente = 0; // 1 para pessoa fisica 2 para pessoa juridica
		this.data_ini     = LocalDate.of(ano, mes, dia);
		this.id_cliente   = id_cliente;
		this.placa        = placa;
		this.data_fim     = LocalDate.of(anoF, mesF, diaF);
		this.situacao     = situacao;
		this.valor_total  = valor_total;

	}
	
	//Métodos acessors
	public int       getTipoCliente() { return this.tipo_cliente; }
	public LocalDate getDataIni()     { return this.data_ini;     }
	public int       getIdCliente()   { return this.id_cliente;   }
	public String    getPlaca()       { return this.placa;        }
	public LocalDate getDataFim()     { return this.data_fim;     }
	public int       getSituacao()    { return this.situacao;     }
	public double    getValorTotal()  { return this.valor_total;  }
	
	//Métodos mutators
	public void setTipoCliente(int tipo_cliente)	  { this.tipo_cliente = tipo_cliente;                }
	public void setDataIni(int dia, int mes, int ano) { this.data_ini     = LocalDate.of(ano, mes, dia); }
	public void setId(int id_cliente)                 { this.id_cliente   = id_cliente;                  }
	public void setPlaca(String placa)                { this.placa        = placa;                       }
	public void setDataFim(int dia, int mes, int ano) { this.data_fim     = LocalDate.of(ano, mes, dia); }
	public void setSituacao(int situacao)	     	  { this.situacao     = situacao;                    }	
	public void setValorTotal(double valor_total)     { this.valor_total  = valor_total;                 }
	
	//Método para imprimir a classe
	public void printAluguel() {
		System.out.println("Data do Aluguel: " + this.getDataIni() + "\nId: "+ this.getIdCliente() + "\nPlaca: " + this.getPlaca());
		System.out.printf("Valor total do aluguel: %.2f\n", this.getValorTotal());
	}
}