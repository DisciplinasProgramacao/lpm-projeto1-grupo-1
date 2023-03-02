import java.util.Scanner;

/**
 * Classe principal do programa de gerenciamento de estoque
 *
 * @author Igor, Lucas
 */
public class Sistema {

	public static void main(String[] args) {
		Lista produtos = new Lista();
		Scanner scan = new Scanner(System.in);
		int escolha = -1;
		while (escolha != 6) {
			System.out.println("\n Digite enter para continuar");
			scan.nextLine();
			System.out.println("\n 0 - inserir produto" + "\n 1 - baixa" + "\n 2 - compra"
					+ "\n 3 - verificar estoque minimo" + "\n 4 - listar" + "\n 5 - listar geral" + "\n 6 - sair");
			while (true)
				try {
					escolha = Integer.parseInt(scan.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("\n Erro, digite um numero");
				}
			switch (escolha) {
				// 0 - inserir produto
				case 0:
					try {
						System.out.println("\n Descricao do produto: ");
						String nome = scan.nextLine();
						System.out.println("\n Custo de compra: ");
						double custo = Double.parseDouble(scan.nextLine());
						System.out.println("\n Lucro (sem porcentagem): ");
						double lucro = Double.parseDouble(scan.nextLine());
						produtos.inserir(new Produto(nome, custo, lucro));
						System.out.println("\n Produto inserido com sucesso");
					} catch (NumberFormatException e) {
						System.out.println("\n Erro, digite um numero");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 1 - baixa de produto
				case 1:
					try {
						System.out.println("\n Descricao do produto: ");
						String nome = scan.nextLine();
						System.out.println("\n Quantidade vendida: ");
						int vendido = Integer.parseInt(scan.nextLine());
						produtos.baixa(nome, vendido);
						System.out.println("\n Dados alterados com sucesso");
					} catch (NumberFormatException e) {
						System.out.println("\n Erro, digite um numero");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 2 - compra de produto
				case 2:
					try {
						System.out.println("\n Descricao do produto: ");
						String nome = scan.nextLine();
						System.out.println("\n Quantidade comprada: ");
						int comprado = Integer.parseInt(scan.nextLine());
						produtos.compra(nome, comprado);
						System.out.println("\n Dados alterados com sucesso");
					} catch (NumberFormatException e) {
						System.out.println("\n Erro, digite um numero");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 3 - verificar estoque minimo
				case 3:
					try {
						System.out.println("\n Descricao do produto: ");
						String nome = scan.nextLine();
						System.out.println("\n Quantidade minima: ");
						int minimo = Integer.parseInt(scan.nextLine());
						if (produtos.get_referencia_produto(nome).suficiente(minimo))
							System.out.println("\n Estoque suficiente");
						else
							System.out.println("\n Estoque insuficiente");
					} catch (NumberFormatException e) {
						System.out.println("\n Erro, digite um numero");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 4 - listar produto
				case 4:
					try {
						System.out.println("\n Descricao do produto: ");
						String nome = scan.nextLine();
						produtos.listar(nome);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				// 5 - listar geral
				case 5:
					if (produtos.vazia())
						System.out.println("\n Nenhum produto cadastrado");
					else
						produtos.listar_geral();
					break;
				// 6 - sair
				case 6:
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