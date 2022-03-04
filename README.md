# Locadora de Carros - Projeto Java
##	O Projeto consiste na implementação de um sistema simplificado para locação de carros.

A locadora aluga **Carros** à **Clientes** previamente cadastrados. Caso o cliente não esteja cadastrado, esta atividade é realizada antes da locação. Um cliente pode ser uma **Pessoa Física** ou uma **Pessoa Jurídica** (outra empresa). 
O cadastro é feito usando os seguintes atributos: 
### Pessoa Física
* CPF 
* Nome
* Endereço
* Telefone

### Pessoa Jurídica
* Razão Social 
* CNPJ
* Nome Fantasia
* Endereço
* Telefone

#### Os clientes foram modelados seguindo uma hieraquia de herança.

Caso um carro disponível seja escolhido por um cliente cadastrado, este é alugado, sendo registrado o período do aluguel. Para que o cliente possa alugar um carro, este não pode estar com dívida pendente (o registro da dívida deve ser mantido).

**Carros são descritos pelos seguintes atributos:**
### Carros
* Placa
* Ano
* Modelo
* Descrição
* Quilometragem
* Situação (disponível para aluguel ou não)
* Taxa Diária
* Observações (informações gerais sobre a situação do carro no momento da locação)
 
O preço base da diária é fixo e determinado pela marca e modelo, mas carros mais novos tem um acréscimo. Carros do ano tem um acréscimo de 50%, carros com até 2 anos tem um acréscimo de 20%, carros mais velhos não sofrem acréscimo. Foi usada uma interface com um método abstrato **calculaValorDiária()** para modelar essa variação de preços entre os carros.

Quando o cliente devolve o carro sua quilometragem é atualizada e um recibo é emitido, baseado nos dias em que ficou com o carro. Ainda, na devolução, caso o cliente não possa pagar, a dívida do aluguel é registrada para cobranças futuras.

O cliente pode a qualquer momento pagar sua dívida atualizando assim sua situação.

O gerente/funcionário do sistema pode emitir vários relatórios. O sistema contempla os seguintes:
### Relatórios
* Relatórios de carros alugados por período.
* Relatório de faturamento por período (valor total dos alugueis em um dado período).
*	Relatório de clientes com dívidas (cliente, valor e carro envolvido).
*	Relatório sobre dados de um cliente específico.
*	Relatório de clientes devedores.

O sistema não tem um banco de dados associado, de modo que no seu fechamento, as informações sobre os clientes, carros e alugueis devem ser salvas, respectivamente, nos arquivos **clientes.txt, carros.txt e alugueis.txt.** Esses arquivos são usados para reinicializar o sistema de seu estado anterior.

Os arquivos devem ser consistentes e sem informações redundantes.
