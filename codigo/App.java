import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Classe principal do programa de gerenciamento de estoque
 *
 * @author Igor, Lucas
 */
public class App {
	/**
	 * Le um inteiro positivo
	 * 
	 * @param scan     Scanner para leitura de dados
	 * @param mensagem Mensagem a ser exibida ao usuario
	 * @return um inteiro positivo
	 */
	static int lerInt(Scanner scan, String mensagem) {
		int out;
		System.out.print(mensagem);
		while (true)
			try {
				out = Integer.parseInt(scan.nextLine());
				if (out < 0)
					throw new NumberFormatException();
				break;
			} catch (NumberFormatException e) {
				System.out.print("\n Erro, digite um numero nao negativo: ");
			}
		return out;
	}

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		String nome = "";
		int escolha = -1;

		Sistema produtos = new Sistema(
				lerInt(scan, "\n Digite o tamanho maximo de produtos no estoque: ") //
		);

		while (escolha != 11) {
			System.out.println("\n Digite enter para continuar");
			scan.nextLine();

			System.out.println("\n 0 - Cadastrar produto" + "\n 1 - Baixa de produto" + "\n 2 - Compra de produto"
					+ "\n 3 - Repor estoque minimo" + "\n 4 - Remover produto" + "\n 5 - Total de produtos"
					+ "\n 6 - Valor total do estoque" + "\n 7 - Listar um produto"
					+ "\n 8 - Produtos abaixo do estoque minimo" + "\n 9 - Listar todos os produtos"
					+ "\n 10 - Adicionar produtos aleatorios" + "\n 11 - Sair do programa");
			escolha = lerInt(scan, "");

			switch (escolha) { // Reduz repeticao no codigo
				case 0:
				case 1:
				case 2:
				case 4:
				case 7:
					System.out.println("\n Descricao do produto: ");
					nome = scan.nextLine();
			}

			switch (escolha) { // Execucao das escolhas
				// 0 - cadastrar produto
				case 0:
					try {
						System.out.println("\n Custo de compra: ");
						double custo = Double.parseDouble(scan.nextLine());
						System.out.println("\n Lucro (sem porcentagem): ");
						double lucro = Double.parseDouble(scan.nextLine());
						System.out.println("\n Estoque minimo: ");
						int minimo = Integer.parseInt(scan.nextLine());
						produtos.cadastrar(new Produto(nome, custo, lucro, minimo));
						System.out.println("\n Produto inserido com sucesso");
					} catch (NumberFormatException e) {
						System.out.print("\n Erro, digite um numero valido: ");
					} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
						System.out.println(e.getMessage());
					}
					break;

				// 1 - baixa de produto
				case 1:
					try {
						System.out.println("\n Quantidade vendida: ");
						int vendido = Integer.parseInt(scan.nextLine());
						produtos.baixa(nome, vendido);
						System.out.println("\n Dados alterados com sucesso");
					} catch (NumberFormatException e) {
						System.out.print("\n Erro, digite um numero valido: ");
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					}
					break;

				// 2 - compra de produto
				case 2:
					try {
						System.out.println("\n Quantidade comprada: ");
						int comprado = Integer.parseInt(scan.nextLine());
						produtos.compra(nome, comprado);
						System.out.println("\n Dados alterados com sucesso");
					} catch (NumberFormatException e) {
						System.out.print("\n Erro, digite um numero valido: ");
					} catch (NoSuchElementException e) {
						System.out.println(e.getMessage());
					}
					break;

				// 3 - repor estoque minimo
				case 3:
					System.out.println("\n " + produtos.repor_estoque() + " produtos comprados");
					break;

				// 4 - remover produto
				case 4:
					try {
						System.out.println("\n Produto \"" + produtos.remover(nome) + "\" removido com sucesso");
					} catch (NoSuchElementException e) {
						System.out.println(e.getMessage());
					}
					break;

				// 5 - total de produtos
				case 5:
					System.out.println("\n Total de produtos: " + produtos.total());
					break;

				// 6 - valor total do estoque
				case 6:
					System.out.format("\n Valor total do estoque: %.2f", produtos.valor_total());
					break;

				// 7 - listar produto
				case 7:
					try {
						produtos.listar_produto(nome);
					} catch (NoSuchElementException e) {
						System.out.println(e.getMessage());
					}
					break;

				// 8 - produtos abaixo do estoque minimo
				case 8:
					if (produtos.vazia())
						System.out.println("\n Nenhum produto cadastrado");
					else {
						int min = produtos.listar_nminimo();
						if (min == 0)
							System.out.println("\n Nenhum produto abaixo do estoque minimo");
						else
							System.out.println("\n " + min + " produtos abaixo do estoque minimo");
					}
					break;

				// 9 - listar geral
				case 9:
					if (produtos.vazia())
						System.out.println("\n Nenhum produto cadastrado");
					else
						produtos.listar_geral();
					break;

				// 10 - adicionar produtos aleatorios
				case 10:
				String nomes[] = { "Arroz", "Feijao", "Macarrao", "Batata", "Cenoura", "Alface", "Tomate", "Cebola", "Alho", "Pimenta", "Azeite", "Sal", "Pimenta-do-reino", "Leite", "Queijo", "Manteiga", "Oleo", "Carne", "Frango", "Peixe", "Biscoito", "Bolacha", "Chocolate", "Cafe", "Cha", "Suco", "Refrigerante", "Agua", "Cerveja", "Vinho", "Vodka", "Whisky", "Cachaça", "Cigarro", "Cigarro eletronico", "Creme dental", "Escova de dente", "Sabonete", "Shampoo", "Condicionador", "Papel higienico", "Papel toalha", "Sabao em po", "Sabao liquido", "Amaciante", "Desinfetante", "Detergente", "Lampada", "Vela", "Candeeiro", "Lanterna", "Bateria", "Carregador", "Chave de fenda", "Chave de boca", "Zap", "Furadeira", "Parafuso", "Porca", "Martelo", "Enxada", "Picareta", "Pregos", "Talhadeira", "Faca", "Garfo", "Colher", "Panela", "Fogao", "Geladeira", "Forno", "Microondas", "Liquidificador", "Sanduicheira", "Torradeira", "Batedeira", "Ferro de passar", "Secador de cabelo", "Ventilador", "Ar condicionado", "Televisao", "Radio", "DVD", "CD player", "MP3", "MP4", "Celular", "Tablet", "Notebook", "Computador", "Impressora", "Scanner", "Monitor", "Mouse", "Teclado", "Cadeira", "Mesa", "Cama", "Colchao", "Travesseiro" }; /* 100 produtos */
				for (String i : nomes)
						produtos.cadastrar(new Produto(i, /* nome */
								Math.random() * 100, /* preco */
								Math.random() * 50 + 30, /* estoque */
								(int) (Math.random() * 1000) /* estoque minimo */
						));
					System.out.println("\n" + nomes.length + " Produtos adicionados com sucesso");
					break;

				// 11 - sair
				case 11:
					System.out.println("\n Saindo...");
			}
		}
		scan.close();
	}

}
