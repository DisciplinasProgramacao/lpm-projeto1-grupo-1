/**
 * Classe que representa uma lista de produtos
 *
 * @author Henrique Almeida
 */
public class Lista {
	private Celula topo;

	/**
	 * Construtor da classe que cria uma lista com o topo como sentinela
	 */
	Lista() {
		this.topo = new Celula(null, null);
	}

	/**
	 * Insere um novo produto na lista
	 *
	 * @param novo_item nome do novo produto
	 * @throws Exception caso o produto ja exista
	 */
	void inserir(Produto novo_item) throws Exception {
		if (get_referencia_cel(novo_item.getDesc()) != null) // verifica se o produto ja existe
			throw new Exception("\n Erro, produto ja existente");
		this.topo.anterior = new Celula(novo_item, this.topo.anterior); // insere antecessor ao topo sentinela, passando
																		// o antigo como anterior do novo item
	}

	/**
	 * Printa as informacoes de todos os produtos
	 *
	 */
	void listar() {
		for (Celula tmp = this.topo.anterior; tmp != null; tmp = tmp.anterior) // percorre a lista em ordem contraria a
																				// de insercao
			tmp.item.print_produto();
	}
}
