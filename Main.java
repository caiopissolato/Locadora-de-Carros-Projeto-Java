import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		ArrayList<Carro> carros      = new ArrayList<Carro>();
		ArrayList<PessoaFisica> pf   = new ArrayList<PessoaFisica>();
		ArrayList<PessoaJuridica> pj = new ArrayList<PessoaJuridica>();
		ArrayList<Aluguel> aluguel   = new ArrayList<Aluguel>();
	    
	    lerCarro("carros.txt", carros);
	    lerPessoaFisica("pessoa_fisica.txt", pf);
	    lerPessoaJuridica("pessoa_juridica.txt", pj);
	    lerAluguel("aluguel.txt", aluguel);
	    		
		boolean x = true;

		while (x) {
			int k = -1;
			menu();
			k = in.nextInt();

			switch (k) {
			
			//RELATÓRIOS
			case 6: 
				int r = 0;	
				boolean op = true;
				while(op) {
					System.out.println("\tRelatórios\n1. Relatório de carros alugados por período \n2. Relatório de faturamento por período \n3. Relatório de clientes com dívidas \n4. Relatório de um cliente \n0. Voltar para o menu anterior");
					r = in.nextInt();
					switch(r) {
					
					//CARROS ALUGADOS POR PERÍODO
					case 1:
						LocalDate dt_ini = null, dt_fim = null;
						int dt_dia = 0, dt_mes = 0, dt_ano = 0;
						System.out.println("\tEntre com a data inícial");
						System.out.println("Dia: ");
						dt_dia = in.nextInt();
						System.out.println("Mês: ");
						dt_mes = in.nextInt();
						System.out.println("Ano: ");
						dt_ano = in.nextInt();
						int j = 0;
						dt_ini = LocalDate.of(dt_ano, dt_mes, dt_dia);
					
						System.out.println("\tEntre com a data final");
						System.out.println("Dia: ");
						dt_dia = in.nextInt();
						System.out.println("Mês: ");
						dt_mes = in.nextInt();
						System.out.println("Ano: ");
						dt_ano = in.nextInt();
						dt_fim = LocalDate.of(dt_ano, dt_mes, dt_dia);
					
						System.out.println("Carros alugados no período de "+ dt_ini.getDayOfMonth() + "/"+ dt_ini.getMonthValue()+
								"/"+ dt_ini.getYear()+" até "+ dt_fim.getDayOfMonth() + "/" + dt_fim.getMonthValue()+ "/"+ dt_fim.getYear() +".");
						for(Aluguel al : aluguel) {
							boolean depois = aluguel.get(j).getDataIni().isAfter(dt_ini);
							boolean antes  = aluguel.get(j).getDataFim().isBefore(dt_fim);
						
							if((depois == true) && (antes == true)) {
								System.out.println(al.getPlaca());
							}
							j++;
						}
						break;					
				
					//FATURAMENTO POR PERÍODO
					case 2:
						LocalDate data_ini = null, data_fim = null;
						int data_dia = 0, data_mes = 0, data_ano = 0;
						double divida_total = 0;
						System.out.println("\tEntre com a data inícial");
						System.out.println("Dia: ");
						data_dia = in.nextInt();
						System.out.println("Mês: ");
						data_mes = in.nextInt();
						System.out.println("Ano: ");
						data_ano = in.nextInt();
						int z = 0;
						data_ini = LocalDate.of(data_ano, data_mes, data_dia);
					
						System.out.println("\tEntre com a data final");
						System.out.println("Dia: ");
						data_dia = in.nextInt();
						System.out.println("Mês: ");
						data_mes = in.nextInt();
						System.out.println("Ano: ");
						data_ano = in.nextInt();
						data_fim = LocalDate.of(data_ano, data_mes, data_dia);
					
						System.out.println("Aluguéis no período de "+ data_ini.getDayOfMonth()+ "/"+ data_ini.getMonthValue()+ "/"+ data_ini.getYear() +" até "+ data_fim.getDayOfMonth()+
								"/"+ data_fim.getMonthValue()+ "/"+ data_fim.getYear() +".");
						
						for(Aluguel al : aluguel) {
							boolean depois = aluguel.get(z).getDataIni().isAfter(data_ini);
							boolean antes  = aluguel.get(z).getDataFim().isBefore(data_fim);
						
							if((depois == true) && (antes == true)) {
								divida_total += al.getValorTotal();
							}
							z++;
						}
						System.out.printf("Faturamento total: %.2f\n", divida_total);
						break;
					
					//CLIENTES COM DÍVIDA
					case 3:
						int i = -1, cont = -1;
						for(Pessoa p : pf) {
							if(p.getDivida() > 0) {
								i++;
								int cpf = pf.get(i).getCpf();
								int ia = procAluguel0(cpf, aluguel);
								System.out.printf("O cliente %d está com uma dívida pendente de R$%.2f relacionado ao aluguel do carro de placa %s.\n", pf.get(i).getCpf(), pf.get(i).getDivida(), aluguel.get(ia).getPlaca());
							}
						}
					
						for(Pessoa p : pj) {
							if(p.getDivida() > 0) {
								cont++;
								int cnpf = pj.get(cont).getCnpj();
								int ia = procAluguel0(cnpf, aluguel);
								System.out.printf("O cliente %d está com uma dívida pendente de R$%.2f relacionado ao aluguel do carro de placa %s.\n", pj.get(cont).getCnpj(), pj.get(cont).getDivida(), aluguel.get(ia).getPlaca());
							}
						}
					
						if(i == -1 && cont == -1) 
							System.out.println("Nenhum cliente com dívida pendente.\n");
					
						break;
				
					//RELATÓRIO DE UM CLIENTE
					case 4:
						System.out.println("Entre com o cpf ou cnpj do cliente: ");
						int identificador = in.nextInt();
						int id_pessoa_fisica   = procPessoaFisica(identificador, pf);
						int id_pessoa_juridica = procPessoaJuridica(identificador, pj);
					
						if(id_pessoa_fisica >= 0) {
							pf.get(id_pessoa_fisica).printPessoaFisica();
							break;
						} else if(id_pessoa_juridica >= 0){
							pj.get(id_pessoa_juridica).printPessoaJuridica();
							break;
						} else {
							System.out.println("Cliente não encontrado.\n");
							break;
						}
						
					case 5:
						String c = "";
						
						clearBuffer(in);
						System.out.println("Entre com a placa do carro: ");
						c = in.nextLine();
						
						int carr = procCarro(c, carros);
						carros.get(carr).printCarro();
						
						break;
						
					case 0: 
						op = false;
						
					}	
				}
				break;
			
			//ALUGAR UM CARRO
			case 1:
				int dia = 0, mes = 0, ano = 0, id = 0;
				String placa = "";

				System.out.println("Entre com o dia do aluguel: ");
				dia = in.nextInt();
				System.out.println("Entre com o mês do aluguel: ");
				mes = in.nextInt();
				System.out.println("Entre com o ano do aluguel: ");
				ano = in.nextInt();
				System.out.println("Entre com o CPF ou CNPJ do cliente: ");
				id = in.nextInt();
				System.out.println("Entre com a placa do carro: ");
				placa = in.next();

				Aluguel alu = new Aluguel(dia, mes, ano, id, placa);

				int car = procCarro(alu.getPlaca(), carros);
				int pessoa_fisica = procPessoaFisica(alu.getIdCliente(), pf);
				int pessoa_juridica = procPessoaJuridica(alu.getIdCliente(), pj);
				int ialu = 0;
				
				if (procCarro(alu.getPlaca(), carros) >= 0) {	// verifica se existe o carro
				
					if (carros.get(car).getSituacao() == true) { // verifica se o carro está alugado no momento
								
						if (procPessoaJuridica(alu.getIdCliente(), pj) >= 0) { // verifica se cliente existe
							
							if (pj.get(pessoa_juridica).getDivida() == 0.0) { // verifica se o cliente está com dívida
								aluguel.add(alu); // adiciona aluguel no ArrayList
								ialu = procAluguel(alu.getPlaca(), alu.getIdCliente(), aluguel);
								aluguel.get(ialu).setSituacao(1); // situacao ativa do aluguel
								aluguel.get(ialu).setTipoCliente(2); // identifica que é um cliente jurídico
								carros.get(car).setSituacao(false); // altera situação do carro para alugado
								escreverAluguel("aluguel.txt", alu);
								escreverArrayCarro("carros.txt", carros);
							} else {
								System.out.println("Pessoa com dívida pendente.\n");
								break;
							}
						
						} else if(procPessoaFisica(alu.getIdCliente(), pf) >= 0) { // verifica se cliente existe
						
							if (pf.get(pessoa_fisica).getDivida() == 0.0) { // verifica se o cliente está com dívida
									aluguel.add(alu); // adiciona aluguel no ArrayList
									ialu = procAluguel(alu.getPlaca(), alu.getIdCliente(), aluguel);
									aluguel.get(ialu).setSituacao(1); // situacao ativa do aluguel
									aluguel.get(ialu).setTipoCliente(1); // identifica que é um cliente físico
									carros.get(car).setSituacao(false);
									escreverAluguel("aluguel.txt", alu);
									escreverArrayCarro("carros.txt", carros);
							} else {
								System.out.println("Pessoa com dívida pendente.\n");
								break;
							}
	
						} else {
							System.out.println("Pessoa não encontrada.\n");							
							break;
						}
					
					} else {
						System.out.println("Carro alugado no momento.\n");						
						break;
					}
				
				} else {
					System.out.println("Carro não encontrado.\n");
					break;
				}
				
				System.out.println("Aluguel feito com sucesso.\n");
				break;

			//DEVOLVER UM CARRO
			case 2:
				double kms = 0.0;
				String Placa = "";
				int ID = -1;
				int d = 0, m = 0, a = 0;

				System.out.println("Placa: ");
				Placa = in.next();
				System.out.println("CPF ou CNPJ: ");
				ID = in.nextInt();

				int icar = procCarro(Placa, carros);
				int ialuguel = procAluguel(Placa, ID, aluguel);
				int ipessoaJuridica = procPessoaJuridica(ID, pj);
				int ipessoaFisica = procPessoaFisica(ID, pf);

				if (ialuguel >= 0) {
					if (aluguel.get(ialuguel).getSituacao() != 2) {
						System.out.println("Dia da devolução: ");
						d = in.nextInt();
						System.out.println("Mês da devolução: ");
						m = in.nextInt();
						System.out.println("Ano da devolução: ");
						a = in.nextInt();
						System.out.println("Quilometragem após devolução: ");
						kms = in.nextDouble();

						double km_ant = carros.get(icar).getKm(); // pegando km antigo

						if (km_ant > kms) {
							System.out.println("Quilometragem errada.\n");
							break;
						}
						
						carros.get(icar).setKm(kms);
						aluguel.get(ialuguel).setSituacao(2); // situação do aluguel encerrada
						carros.get(icar).setSituacao(true);
						aluguel.get(ialuguel).setDataFim(d, m, a);
						escreverArrayCarro("carros.txt", carros);
						
						long dias = ChronoUnit.DAYS.between(aluguel.get(ialuguel).getDataIni(),
								aluguel.get(ialuguel).getDataFim());// calcula diferença entre a data do aluguel até a data da devolução
						
						if(dias <= 0) {
							System.out.println("Data inválida.\n");
							break;
						}

						System.out.println("\tRECIBO \nO cliente " + ID + " ficou com o \ncarro de placa " + Placa
								+ " dos dias \n" + aluguel.get(ialuguel).getDataIni().getDayOfMonth() + "/"+ aluguel.get(ialuguel).getDataIni().getMonthValue() + "/" + aluguel.get(ialuguel).getDataIni().getYear()+ " até o dia "
								+ aluguel.get(ialuguel).getDataFim().getDayOfMonth() + "/" + aluguel.get(ialuguel).getDataFim().getMonthValue() + "/" + aluguel.get(ialuguel).getDataIni().getYear() + " \ne percorreu " + (kms - km_ant)
								+ " quilometros.\n\n");
						
						
						double div = dias * carros.get(icar).calculaValorDiario();
						int pagamento = 0;
						double valor = 0;
						
						//double div_final = carros.get(icar).calculaValorDiario();
						
						int tipo = aluguel.get(ialuguel).getTipoCliente();
						
						//salvando dívida no sistema
						if (tipo == 1) {
							pf.get(ipessoaFisica).setDivida(pf.get(ipessoaFisica).getDivida() + div);		
						} else if (tipo == 2) {
							pj.get(ipessoaJuridica).setDivida(pj.get(ipessoaJuridica).getDivida() + div);
						}
						
						aluguel.get(ialuguel).setValorTotal(div);
						escreverArrayAluguel("aluguel.txt", aluguel);
						
						System.out.printf("O valor ficou em R$%.2f deseja realizar o pagamento agora?", div);
						System.out.println("\n1.Sim \n2.Não");
						pagamento = in.nextInt();

						switch (pagamento) {
							case 1:
								if(aluguel.get(ialuguel).getTipoCliente() == 1) {
									System.out.println("Valor pago: ");
									valor = in.nextDouble();
									pf.get(ipessoaFisica).setDivida(div - valor);
								
									if(pf.get(ipessoaFisica).getDivida() < 0) {
										pf.get(ipessoaFisica).setDivida(0);
									}
								
									System.out.printf("Dívida final do cliente: %.2f\n", pf.get(ipessoaFisica).getDivida());
									escreverArrayPessoaFisica("pessoa_fisica.txt", pf);
									break;
							
								} else if (aluguel.get(ialuguel).getTipoCliente() == 2) {
									System.out.println("Valor pago: ");
									valor = in.nextDouble();
									pj.get(ipessoaJuridica).setDivida(div - valor);
								
									if(pj.get(ipessoaJuridica).getDivida() < 0) {
										pj.get(ipessoaJuridica).setDivida(0);
									}
								
									System.out.printf("Dívida final do cliente: %.2f\n", pj.get(ipessoaJuridica).getDivida());
									escreverArrayPessoaJuridica("pessoa_juridica.txt", pj);
									break;
								}							
							default:
								escreverArrayPessoaFisica("pessoa_fisica.txt", pf);
								escreverArrayPessoaJuridica("pessoa_juridica.txt", pj);
								System.out.println("\nDívida salva no sistema.\n");
							}// fim switch
									
						} else {
							System.out.println("Aluguel já encerrado.\n");
							break;
						}
				
					} else {
						System.out.println("Aluguel não encontrado\n");
						break;
					}
				
					System.out.println("Aluguel encerrado com sucesso.\n");
					break;

			//CADASTRAR UM CLIENTE
			case 3:
				String nome_fantasia = "", razao_social = "", endereco = "";
				int Id = -1, telefone = 0, opc = 0;

				System.out.println("\tEscolha uma opção \n1.Pessoa Física \n2.Pessoa Jurídica");
				opc = in.nextInt();
				clearBuffer(in);
				if (opc == 1) {

					System.out.println("Nome: ");
					nome_fantasia = in.nextLine();
					System.out.println("Cpf: ");
					Id = in.nextInt();
					clearBuffer(in);
					System.out.println("Endereço: ");
					endereco = in.nextLine();
					System.out.println("Telefone: ");
					telefone = in.nextInt();
					//clearBuffer(in);					
					
					PessoaFisica pfis = new PessoaFisica(nome_fantasia, Id, endereco, telefone);

					if (procPessoaFisica(pfis.getCpf(), pf) >= 0) {
						System.out.println("CPF já cadastrado.\n");
						break;
					} else {
						pf.add(pfis);
						escreverPessoaFisica("pessoa_fisica.txt", pfis);
						System.out.println("Cliente Físico cadastrado com sucesso.\n");
						break;
					}
				
				} else if (opc == 2) {

					System.out.println("Razão Social: ");
					razao_social = in.nextLine();
					System.out.println("Cnpj: ");
					Id = in.nextInt();
					clearBuffer(in);
					System.out.println("Nome fantasia: ");
					nome_fantasia = in.nextLine();
					System.out.println("Telefone: ");
					telefone = in.nextInt();
					clearBuffer(in);
					System.out.println("Endereço: ");
					endereco = in.nextLine();
					PessoaJuridica pjur = new PessoaJuridica(razao_social, Id, nome_fantasia, endereco, telefone);

					if (procPessoaJuridica(pjur.getCnpj(), pj) >= 0) {
						System.out.println("CNPJ já cadastrado.\n");
						break;
					} else {
						pj.add(pjur);
						escreverPessoaJuridica("pessoa_juridica.txt", pjur);
						System.out.println("Cliente Jurídico cadastrado com sucesso.\n");
						break;
					}
				} else {
					System.out.println("Opção inválida.\n");
					break;
				}

			//CADASTRAR UM CARRO
			case 4:
				int Ano = 0;
				String modelo = "", plac = "", descricao = "", obs = "";
				double km = 0.0, tx = 0.0;
				boolean situacao = true;

				clearBuffer(in);
				System.out.println("Placa: ");
				plac = in.nextLine();
				System.out.println("Ano: ");
				Ano = in.nextInt();
				clearBuffer(in);
				System.out.println("Modelo: ");
				modelo = in.nextLine();
				System.out.println("Descrição: ");
				descricao = in.nextLine();
				System.out.println("KM: ");
				km = in.nextDouble();
				situacao = true;
				System.out.println("Taxa diária: ");
				tx = in.nextDouble();
				clearBuffer(in);
				System.out.println("Observação: ");
				obs = in.nextLine();

				Carro c = new Carro(plac, Ano, modelo, descricao, km, situacao, tx, obs);
				if (procCarro(c.getPlaca(), carros) >= 0) {
					System.out.println("Carro já cadastrado.\n");
					break;
				} else {
					carros.add(c);
					escreverCarro("carros.txt", c);
				}
				
				System.out.println("Carro cadastrado com sucesso.\n");
				break;

			//PAGAMENTO DE DÍVIDAS
			case 5:
				int ide = 0;
				double v = 0, divida = 0;
				System.out.println("Entre com o CPF ou CNPJ: ");
				ide = in.nextInt();
				int enc_pessoa_fisica = procPessoaFisica(ide, pf);
				int enc_pessoa_juridica = procPessoaJuridica(ide, pj);
				
				if (enc_pessoa_fisica >= 0) {
					System.out.println("Sua dívida é de " + pf.get(enc_pessoa_fisica).getDivida());
					System.out.println("Valor pago: ");
					v = in.nextDouble();
					divida = pf.get(enc_pessoa_fisica).getDivida();
					pf.get(enc_pessoa_fisica).setDivida(divida - v);
					
					if(pf.get(enc_pessoa_fisica).getDivida() < 0) {
						pf.get(enc_pessoa_fisica).setDivida(0);
					}
					
					System.out.println("Dívida final do cliente: " + pf.get(enc_pessoa_fisica).getDivida());
					escreverArrayPessoaFisica("pessoa_fisica.txt", pf);
					break;
				} else if (enc_pessoa_juridica >= 0) {
					System.out.println("Sua dívida é de " + pj.get(enc_pessoa_juridica).getDivida());
					System.out.println("Valor pago: ");
					v = in.nextDouble();
					divida = pj.get(enc_pessoa_juridica).getDivida();
					pj.get(enc_pessoa_juridica).setDivida(divida - v);
					
					if(pj.get(enc_pessoa_juridica).getDivida() < 0) {
						pj.get(enc_pessoa_juridica).setDivida(0);
					}
					
					System.out.println("Dívida final do cliente: " + pj.get(enc_pessoa_juridica).getDivida());
					escreverArrayPessoaJuridica("pessoa_juridica.txt", pj);
					break;
				}
				
			default:
				escreverArrayCarro("carros.txt", carros);
				escreverArrayPessoaFisica("pessoa_fisica.txt", pf);
				escreverArrayPessoaJuridica("pessoa_juridica.txt", pj);
				escreverArrayAluguel("aluguel.txt", aluguel);
				x = false;
			}
		}
	}

	public static void menu() {
		System.out.println(
				"\tDigite uma opção \n1. Alugar um carro \n2. Devolver um carro \n3. Cadastrar um cliente \n4. Cadastrar um carro  \n5. Pagar dívida pendente \n6. Relatórios \n0. Sair");
	}

	public static int procCarro(String placa, ArrayList<Carro> c) {
		int i = 0;
		
		for (Carro car : c) {
			if (car.getPlaca().equalsIgnoreCase(placa)) {
				return i;
			}
			i++;
		} 
		
		return -1;
	}

	public static int procPessoaFisica(int cpf, ArrayList<PessoaFisica> list) {
		int i = 0;
		
		for (PessoaFisica p : list) {
			if (p.getCpf() == cpf) {
				return i;
			}
			i++;
		}

		return -1;
	}

	public static int procPessoaJuridica(int cnpj, ArrayList<PessoaJuridica> list) {
		int i = 0;
		
		for (PessoaJuridica p : list) {
			if (p.getCnpj() == cnpj) {
				return i;
			}
			i++;
		}
		
		return -1;
	}

	public static int procAluguel(String placa, int id, ArrayList<Aluguel> alu) {
		int i = 0;
		
		for (Aluguel a : alu) {
			if ((a.getPlaca().equalsIgnoreCase(placa)) && a.getIdCliente() == id) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public static int procAluguel0(int id, ArrayList<Aluguel> alu) {
		int i = 0;
			
		for (Aluguel a : alu) {
			if (a.getIdCliente() == id) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public static void escreverCarro(String caminho, Carro c) throws IOException {
		FileWriter arq = new FileWriter(caminho, true);
	    PrintWriter gravarArq = new PrintWriter(arq);		    	    
	    	
	    gravarArq.println(c.getPlaca() + ";"+ c.getAno() + ";"+ c.getModelo() +";"+ c.getDescricao() +";"+ c.getKm() +";"+ c.getSituacao() +";"+ c.getTx_dia() +";"+ c.getObs());	    
	    
	    arq.close();
	}
	
	public static void escreverArrayCarro(String caminho, ArrayList<Carro> c) throws IOException {
		FileWriter arq = new FileWriter(caminho);
		PrintWriter gravarArq = new PrintWriter(arq);
		
		for(int i = 0; i < c.size(); i++) {
			gravarArq.println(c.get(i).getPlaca() + ";"+ c.get(i).getAno() + ";"+ c.get(i).getModelo() +";"+ c.get(i).getDescricao() +";"+ c.get(i).getKm() +";"+ c.get(i).getSituacao() +";"+ c.get(i).getTx_dia() +";"+ c.get(i).getObs());
		}
		
		gravarArq.close();
	}
	
	public static void escreverPessoaFisica(String caminho, PessoaFisica pf) throws IOException {
		FileWriter arq = new FileWriter(caminho, true);
	    PrintWriter gravarArq = new PrintWriter(arq);		    	    
	    	
	    gravarArq.println(pf.getNome() + ";"+ pf.getCpf() + ";"+ pf.getEndereco() +";"+ pf.getTelefone()+ ";"+ pf.getDivida());	    
	    
	    arq.close();
	}
	
	public static void escreverArrayPessoaFisica(String caminho, ArrayList<PessoaFisica> pf) throws IOException {
		FileWriter arq = new FileWriter(caminho);
		PrintWriter gravarArq = new PrintWriter(arq);
		
		for(int i = 0; i < pf.size(); i++) {
			gravarArq.println(pf.get(i).getNome() + ";"+ pf.get(i).getCpf() + ";"+ pf.get(i).getEndereco() +";"+ pf.get(i).getTelefone()+ ";"+ pf.get(i).getDivida());
		}
		
		gravarArq.close();
	}
	
	public static void escreverPessoaJuridica(String caminho, PessoaJuridica pj) throws IOException {
		FileWriter arq = new FileWriter(caminho, true);
	    PrintWriter gravarArq = new PrintWriter(arq);		    	    
	    	
	    gravarArq.println(pj.getRazaoSocial() + ";"+ pj.getCnpj() + ";"+ pj.getNomeFantasia() +";"+ pj.getEndereco() +";"+ pj.getTelefone()+ ";"+ pj.getDivida());	    
	    
	    arq.close();
	}

	public static void escreverArrayPessoaJuridica(String caminho, ArrayList<PessoaJuridica> pj) throws IOException {
		FileWriter arq = new FileWriter(caminho);
		PrintWriter gravarArq = new PrintWriter(arq);
		
		for(int i = 0; i < pj.size(); i++) {
			gravarArq.println(pj.get(i).getRazaoSocial() + ";"+ pj.get(i).getCnpj() + ";"+ pj.get(i).getNomeFantasia() +";"+ pj.get(i).getEndereco() +";"+ pj.get(i).getTelefone()+ ";"+ pj.get(i).getDivida());
		}
		
		gravarArq.close();
	}
	
	public static void escreverAluguel(String caminho, Aluguel a) throws IOException {
		FileWriter arq = new FileWriter(caminho, true);
	    PrintWriter gravarArq = new PrintWriter(arq);		    	    
	    	
	    gravarArq.println(a.getDataIni().getDayOfMonth()+";"+ a.getDataIni().getMonth().getValue()+ ";"+ a.getDataIni().getYear() +";"+ a.getIdCliente() + ";"+ a.getPlaca()+ ";"+ a.getSituacao()+ ";"+ a.getValorTotal());	    
	    
	    arq.close();
	}

	public static void escreverArrayAluguel(String caminho, ArrayList<Aluguel> a) throws IOException {
		FileWriter arq = new FileWriter(caminho);
		PrintWriter gravarArq = new PrintWriter(arq);
		
		for(int i = 0; i < a.size(); i++) {
			gravarArq.println(a.get(i).getDataIni().getDayOfMonth()+";"+ a.get(i).getDataIni().getMonth().getValue()+ ";"+ a.get(i).getDataIni().getYear() +";"+ a.get(i).getIdCliente() + ";"+ a.get(i).getPlaca()
					+ ";"+ a.get(i).getSituacao() +";"+ a.get(i).getValorTotal()+ ";"+ a.get(i).getDataFim().getDayOfMonth() +";"+ a.get(i).getDataFim().getMonthValue() +";"+ a.get(i).getDataFim().getYear());
		}
		
		gravarArq.close();
	}
	
	public static void lerCarro(String caminho, ArrayList<Carro> cList) throws IOException {
		try {
			FileInputStream arquivo    = new FileInputStream(caminho);
			InputStreamReader input    = new InputStreamReader(arquivo);
			BufferedReader lerArq      = new BufferedReader(input);
			String linha;
			Carro carro;
			String placa, modelo, descricao, obs;
			int ano;
			double km, tx_dia;
			boolean situacao;

			do { 
				linha = lerArq.readLine();
				
				if(linha != null) {
					String[] all = linha.split(";");
				
					placa     = all[0];
					ano       = Integer.parseInt(all[1]);
					modelo    = all[2];
					descricao = all[3];
					km        = Double.parseDouble(all[4]);
					situacao  = Boolean.parseBoolean(all[5]);
					tx_dia    = Double.parseDouble(all[6]);
					obs       = all[7];
				
					carro = new Carro(placa, ano, modelo, descricao, km, situacao, tx_dia, obs);
					cList.add(carro);
				}
				
			} while(linha != null);
	    
			lerArq.close();
		
		} catch (Exception e) {
			System.out.println("Erro ao ler o arquivo.");
		}
	}
	
	public static void lerPessoaFisica(String caminho, ArrayList<PessoaFisica> pf) throws IOException {
		try {
			FileInputStream arquivo    = new FileInputStream(caminho);
			InputStreamReader input    = new InputStreamReader(arquivo);
			BufferedReader lerArq      = new BufferedReader(input);
			String linha;
			PessoaFisica pfis;
			String nome, endereco;
			int cpf, telefone;
			double divida;

			do { 
				linha = lerArq.readLine();
				
				if(linha != null) {
					String[] all = linha.split(";");
				
					nome     = all[0];
					cpf      = Integer.parseInt(all[1]);
					endereco = all[2];
					telefone = Integer.parseInt(all[3]);
					divida   = Double.parseDouble(all[4]);
				
					pfis = new PessoaFisica(nome, cpf, endereco, telefone, divida);
					pf.add(pfis);
				}
				
			} while(linha != null);
	    
			lerArq.close();
		
		} catch (Exception e) {
			System.out.println("Erro ao ler o arquivo.");
		}
	}
	
	public static void lerPessoaJuridica(String caminho, ArrayList<PessoaJuridica> pj) throws IOException {
		try {
			FileInputStream arquivo    = new FileInputStream(caminho);
			InputStreamReader input    = new InputStreamReader(arquivo);
			BufferedReader lerArq      = new BufferedReader(input);
			String linha;
			PessoaJuridica pjur;
			double divida;
			String nome_fantasia, razao_social, endereco;
			int cnpj, telefone;
			

			do { 
				linha = lerArq.readLine();
				
				if(linha != null) {
					String[] all = linha.split(";");
				
					razao_social  = all[0];
					cnpj          = Integer.parseInt(all[1]);
					nome_fantasia = all[2];
					endereco      = all[3];
					telefone      = Integer.parseInt(all[4]);
					divida        = Double.parseDouble(all[5]);
				
					pjur = new PessoaJuridica(razao_social, cnpj, nome_fantasia, endereco, telefone, divida);
					pj.add(pjur);
				}
				
			} while(linha != null);
	    
			lerArq.close();
		
		} catch (Exception e) {
			System.out.println("Erro ao ler o arquivo.");
		}
	}

	public static void lerAluguel(String caminho, ArrayList<Aluguel> a) throws IOException {
		try {
			FileInputStream arquivo    = new FileInputStream(caminho);
			InputStreamReader input    = new InputStreamReader(arquivo);
			BufferedReader lerArq      = new BufferedReader(input);
			String linha;
			Aluguel alu;
			String placa;
			int dia, diaF, mes, mesF, ano, anoF, id, situacao;
			double valor_total;

			do { 
				linha = lerArq.readLine();
				
				if(linha != null) {
					String[] all = linha.split(";");
				
					dia         = Integer.parseInt(all[0]);
					mes         = Integer.parseInt(all[1]);
					ano         = Integer.parseInt(all[2]);
					id          = Integer.parseInt(all[3]);
					placa       = all[4];
					situacao    = Integer.parseInt(all[5]);
					valor_total = Double.parseDouble(all[6]);
					diaF        = Integer.parseInt(all[7]);
					mesF        = Integer.parseInt(all[8]);
					anoF        = Integer.parseInt(all[9]);					
				
					alu = new Aluguel(dia, mes, ano, id, placa, situacao, valor_total, diaF, mesF, anoF);
					a.add(alu);
				}
				
			} while(linha != null);
	    
			lerArq.close();
		
		} catch (Exception e) {
			System.out.println("Erro ao ler o arquivo.");
		}
	}
	
	private static void clearBuffer(Scanner scanner) {
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}
	}
}