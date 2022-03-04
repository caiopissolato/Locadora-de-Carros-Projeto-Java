import java.time.LocalDate;

public class Carro implements CalculaValor {
	private String  placa;
	private int     ano;
	private String  modelo;
	private String  descricao;
	private double  km;
	private boolean situacao;
	private double  tx_dia;
	private String  obs;
	
	//Construtores
	public Carro(String placa, int ano, String modelo, String descricao, double km, boolean situacao, double tx_dia, String obs) {
		this.placa     = placa;
		this.ano       = ano;
		this.modelo    = modelo;
		this.descricao = descricao;
		this.km        = km;
		this.situacao  = situacao;
		this.tx_dia    = tx_dia;
		this.obs       = obs;
	}
	
	public Carro() {
		this.placa     = " ";
		this.ano       = 0;
		this.modelo    = " ";
		this.descricao = " ";
		this.km        = 0.0;
		this.situacao  = true;
		this.tx_dia    = 0.0;
		this.obs       = " ";
	}
	
	//Métodos acessors
	public String  getPlaca()     { return this.placa;     }
	public int     getAno()       { return this.ano;       }
	public String  getModelo()    { return this.modelo;    }
	public String  getDescricao() { return this.descricao; }
	public double  getKm()        { return this.km;        }
	public boolean getSituacao()  { return this.situacao;  }
	public double  getTx_dia()    { return this.tx_dia;    }
	public String  getObs()       { return this.obs;       }
	
	//Métodos mutators
	public void setPlaca(String placa)         { this.placa     = placa;     }
	public void setAno(int ano)                { this.ano       = ano;       }
	public void setModelo(String modelo)       { this.modelo    = modelo;    }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public void setKm(double km)               { this.km        = km;        }
	public void setSituacao(boolean situacao)  { this.situacao  = situacao;  }
	public void setTx_dia(double tx_dia)       { this.tx_dia    = tx_dia;    }
	public void setObs(String obs)             { this.obs       = obs;       }
	
	public void printCarro() {
		if(this.getSituacao()) {
			System.out.printf("Placa: %s \nAno: %d \nModelo: %s \nDescricao: %s \nKM: %.2f \nSituacao: Disponivel \nTaxa Diaria: %.2f \nObservacao: %s\n\n",
				this.getPlaca(), this.getAno(), this.getModelo(), this.getDescricao(), this.getKm(), this.getTx_dia(), this.getObs());
		} else {
			System.out.printf("Placa: %s \nAno: %d \nModelo: %s \nDescricao: %s \nKM: %.2f \nSituacao: Alugado \nTaxa Diaria: %.2f \nObservacao: %s\n\n",
					this.getPlaca(), this.getAno(), this.getModelo(), this.getDescricao(), this.getKm(), this.getTx_dia(), this.getObs());
		}
	}

	@Override //INTERFACE
	public double calculaValorDiario() {
		LocalDate hoje = LocalDate.now();
		Carro c = new Carro();
		
		if(this.getAno() == hoje.getYear()) {
			c.setTx_dia((this.getTx_dia() / 2) + this.getTx_dia());
			return c.getTx_dia();
		
		} else if((this.getAno() == (hoje.getYear() - 2)) || (this.getAno() == (hoje.getYear() - 1))) {
			c.setTx_dia((this.getTx_dia() * 0.2) + this.getTx_dia());
			return c.getTx_dia();
		
		} else {
			return this.getTx_dia();
		}
	}
}