import java.util.Scanner;

/**
 * Classe principal do programa de gerenciamento de estoque
 *
 * @author Igor, Lucas
 */
public class Sistema {
	static int estoque_minimo; // Estoque minimo

	public static void main(String[] args) {
		Lista produtos = new Lista();
		Scanner scan = new Scanner(System.in);
		int escolha = -2;
		System.out.print("\n Digite o valor minimo do estoque: ");
		while (true)
			try {
				estoque_minimo = Integer.parseInt(scan.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.print("\n Erro, digite um numero valido: ");
			}
		while (escolha != -1) {
			System.out.println("\n Digite enter para continuar");
			scan.nextLine();
			System.out.println("\n 0 - Inserir produto" + "\n 1 - Baixa" + "\n 2 - Compra"
					+ "\n 3 - Verificar estoque minimo" + "\n 4 - Total de produtos" + "\n 5 - Remover item"
					+ "\n 6 - Listar produtos sem estoque" + "\n 7 - Valor total do estoque"
					+ "\n 8 - Listar um produto" + "\n 9 - Listar todos os produtos" + "\n -1 - sair");
			while (true)
				try {
					escolha = Integer.parseInt(scan.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.print("\n Erro, digite um numero valido: ");
				}
			String nome = "";
			switch (escolha) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 5:
				case 8:
					System.out.println("\n Descricao do produto: ");
					nome = scan.nextLine();
					break;
			}
			switch (escolha) {
				// 0 - inserir produto
				case 0:
					try {
						System.out.println("\n Custo de compra: ");
						double custo = Double.parseDouble(scan.nextLine());
						System.out.println("\n Lucro (sem porcentagem): ");
						double lucro = Double.parseDouble(scan.nextLine());
						produtos.inserir(new Produto(nome, custo, lucro));
						System.out.println("\n Produto inserido com sucesso");
					} catch (NumberFormatException e) {
						System.out.print("\n Erro, digite um numero valido: ");
					} catch (Exception e) {
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
					} catch (Exception e) {
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
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 3 - verificar estoque minimo
				case 3:
					try {
						if (produtos.get_referencia_produto(nome).suficiente(estoque_minimo))
							System.out.println("\n Estoque suficiente");
						else
							System.out.println("\n Estoque insuficiente");
					} catch (NumberFormatException e) {
						System.out.print("\n Erro, digite um numero valido: ");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 4 - total de produtos
				case 4:
					System.out.println("\n Total de produtos: " + produtos.total());
					break;
				// 5 - remover produto
				case 5:
					try {
						System.out.println("\n Produto " + produtos.remover(nome) + " removido com sucesso");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 6 - produtos abaixo do estoque minimo
				case 6:
					if (produtos.vazia())
						System.out.println("\n Nenhum produto cadastrado");
					else
						produtos.listar_nminimo();
					break;
				// 7 - valor total do estoque
				case 7:
					System.out.println("\n Valor total do estoque: " + produtos.valor_total());
					break;
				// 8 - listar produto
				case 8:
					try {
						produtos.listar(nome);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 9 - listar geral
				case 9:
					if (produtos.vazia())
						System.out.println("\n Nenhum produto cadastrado");
					else
						produtos.listar_geral();
					break;
				// -1 - sair
				case -1:
					System.out.println("\n Saindo...");
					break;
				default:
					System.out.println("\n Opcao invalida");
					break;
			}
		}
		scan.close();
	}

}
