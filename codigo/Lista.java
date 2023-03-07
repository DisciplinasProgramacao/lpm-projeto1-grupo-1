import java.util.NoSuchElementException;

/**
 * Classe que representa uma lista de produtos
 *
 * @author Henrique Almeida
 */
class Lista {
	private Celula topo;

	/**
	 * Construtor da classe que cria uma lista com o topo como sentinela
	 */
	Lista() {
		this.topo = new Celula(null, null);
	}

	/**
	 * Retorna a referencia para uma celula, usado em
	 * {@link #get_produto(String)} e {@link #inserir(Produto)}
	 *
	 * @param in nome do produto
	 * @return referencia para a celula ou null caso nao seja encontrada
	 */
	private Celula get_cel(String in) {
		Celula out = this.topo.anterior;
		while (out != null && !out.item.get_desc().equalsIgnoreCase(in))
			out = out.anterior;
		return out;
	}

	/**
	 * Incrementa o total de vendas do produto
	 *
	 * @param in nome do produto
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 * @throws IllegalStateException  caso nao haja produtos o suficiente
	 */
	void baixa(String in, int vendido) throws NoSuchElementException, IllegalStateException {
		get_produto(in).baixa(vendido);
	}

	/**
	 * Incrementa o total de compras do produto
	 *
	 * @param in       nome do produto
	 * @param comprado quantidade comprada
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 */
	void compra(String in, int comprado) throws NoSuchElementException {
		get_produto(in).compra(comprado);
	}

	/**
	 * @param in nome do produto
	 * @return Referencia para o produto
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 */
	Produto get_produto(String in) throws NoSuchElementException {
		Celula out = get_cel(in);
		if (out == null)
			throw new NoSuchElementException("\n Erro, produto nao encontrado");
		return out.item;
	}

	/**
	 * Insere um novo produto na lista
	 *
	 * @param novo_item nome do novo produto
	 * @throws IllegalArgumentException caso o produto ja exista
	 */
	void inserir(Produto novo_item) throws IllegalArgumentException {
		if (get_cel(novo_item.get_desc()) != null)
			throw new IllegalArgumentException("\n Erro, produto ja existente");
		this.topo.anterior = new Celula(novo_item, this.topo.anterior);
	}

	/**
	 * @return true se a lista estiver vazia, false caso contrario
	 */
	boolean vazia() {
		return this.topo.anterior == null;
	}

	/**
	 * Printa as informacoes do produto
	 *
	 * @param in nome do produto
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 */
	void listar_produto(String in) throws NoSuchElementException {
		get_produto(in).print_produto();
	}

	/**
	 * Printa as informacoes de todos os produtos
	 */
	void listar_geral() {
		for (Celula tmp = this.topo.anterior; tmp != null; tmp = tmp.anterior)
			tmp.item.print_produto();
	}

	/**
	 * Printa as informacoes de todos os produtos com quantidade menor que o minimo
	 * estabelecido
	 */
	void listar_nminimo() {
		for (Celula tmp = this.topo.anterior; tmp != null; tmp = tmp.anterior)
			if (!tmp.item.suficiente(Sistema.estoque_minimo))
				tmp.item.print_produto();
	}

	/**
	 * @return quantidade de produtos na lista
	 */
	int total() {
		int out = 0;
		for (Celula tmp = this.topo.anterior; tmp != null; tmp = tmp.anterior)
			out++;
		return out;
	}

	/**
	 * @return valor total dos produtos na lista
	 */
	double valor_total() {
		double out = 0;
		for (Celula tmp = this.topo.anterior; tmp != null; tmp = tmp.anterior)
			out += tmp.item.get_valor_estocado();
		return out;
	}

	/**
	 * Remove um produto da lista
	 *
	 * @param nome nome do produto
	 * @return nome do produto removido
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 */
	String remover(String nome) throws NoSuchElementException {
		Celula tmp = this.topo; // tmp comeca no topo
		while (tmp.anterior != null && !tmp.anterior.item.get_desc().equalsIgnoreCase(nome)) // ao achar o produto
			tmp = tmp.anterior; // procura o produto
		if (tmp.anterior == null) // se nao for encontrado
			throw new NoSuchElementException("\n Erro, produto nao encontrado");
		tmp.anterior = tmp.anterior.anterior; // remove o produto
		return nome;
	}
}
