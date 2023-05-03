[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-f4981d0f882b2a3f0472912d15f9806d57e124e0fc890972558857b51b24a6f9.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10074294)
# StockMaster: Controle eficiente de estoque
No projeto StockMaster: Controle eficiente de estoque, o objetivo é garantir que a empresa possua a quantidade adequada de produtos em seu estoque para atender à demanda de seus clientes, minimizando as perdas e os custos de armazenagem.
Para isso, é necessário monitorar constantemente os níveis de estoque, controlar o fluxo de entrada e saída de mercadorias e manter valores precisos através das operações de estoque.
Com uma gestão eficiente de estoque, a empresa pode garantir melhora operacional, reduçao dos custos e melhora da satisfação dos clientes ao garantir a disponibilidade dos produtos no momento certo.

## Nota base do grupo: 13,75

A nota final, que é individual, se dará pela nota acima, multiplicada por um peso entre 0 e 1 relativo ao acompanhamento semanal do projeto. Lembre-se: não é só a entrega do produto finalizado que importa, é todo o processo de sua construção e as entregas parciais para o “cliente”.

## Comentários

### Diagrama + aderência das classes ao diagrama: 2/2 pontos 

### Requisitos corretamente implementados: 5,5/6 pontos 
	- produto (preço, estoque) - 2 / 2

	- estoque (valor, abaixo do estoque) - 1,6 / 2
	Classe Sistema
	Poderiam ter detalhado melhor o balanço simplificado

	- sistema (vender, comprar, consultas) - 1,9/2
	Classe App
	Opção 10 - Adicionar produtos aleatorios não está funcionando

### Documentação de código: 3 pontos 
	
	
### Testes (quantidade e qualidade): 3,25/4 pontos 
	- produto: 1,5/2 pontos
	Se o insereProdutos() é um método para facilitar os testes, não usar notação @Test - Talvez seria melhor tentar pensar no uso do @Before
	testaCompraVendaDeItemComprado() sem assert
	Ficou confuso entendimento do testaValorTotal() baseado nos comentários realizados
	- estoque: 1,75/2 pontos

	Observação:
	O recomendado seria criar uma classe para cada elementos testado, assim como em um source folder diferente
	
  
## Alunos integrantes da equipe

* Gabriel Dolabela Marques
* Henrique Carvalho Almeida
* Igor Pinheiro dos Santos
* João Pedro de Oliveira Pauletti
* Lucas Monteiro Lima

## Professores responsáveis

* Cleiton Silva Tavares
