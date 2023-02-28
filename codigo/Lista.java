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
		this.topo = new Celula(null,null);
	}

	// metodos privados

	/**
	 * Verifica se o lucro esta entre 30 e 80, usado em
	 * {@link #alterar_preco(String, double)} e
	 * {@link #alterar_custo(String, double, double)}
	 *
	 * @param in lucro
	 * @return lucro
	 * @throws Exception caso o lucro seja invalido
	 */
	private double verifica_lucro(double in) throws Exception {
		if (in < 30 || in > 80)
			throw new Exception("\n Margem de lucro invalida, apenas entre 30 e 80");
		return in;
	}

	/**
	 * Retorna a referencia para uma celula, usado em
	 * {@link #get_referencia_produto(String)}
	 *
	 * @param in nome do produto
	 * @return referencia para a celula ou null caso nao seja encontrada
	 */
	private Celula get_referencia_cel(String in) {
		Celula out = this.topo.anterior;
		while (out != null && !out.item.getDesc().equalsIgnoreCase(in))
			out = out.anterior;
		return out;
	}

	// fim dos metodos privados

	/**
	 * @param in nome do produto
	 * @return Referencia para o produto
	 * @throws Exception caso o produto nao seja encontrado
	 */
	Produto get_referencia_produto(String in) throws Exception {
		Celula out = get_referencia_cel(in);
		if (out == null)
			throw new Exception("\n Erro, produto nao encontrado");
		return out.item;
	}

	/**
	 * Insere um novo produto na lista
	 *
	 * @param novo_item nome do novo produto
	 * @throws Exception caso o produto ja exista
	 */
	void inserir(Produto novo_item) throws Exception {
		if (get_referencia_cel(novo_item.getDesc()) != null)
			throw new Exception("\n Erro, produto ja existente");
		this.topo.anterior = new Celula(novo_item, this.topo.anterior);
	}

	/**
	 * Altera o custo de venda do produto
	 *
	 * @param in         nome do produto
	 * @param novo_lucro novo lucro
	 * @throws Exception caso o produto nao seja encontrado ou o novo lucro seja
	 *                   invalido
	 */
	void alterar_preco(String in, double novo_lucro) throws Exception {
		Produto ref = get_referencia_produto(in);
		ref.setPreco(ref.getCusto() * 1.18 * verifica_lucro(novo_lucro) / 100);
	}

	/**
	 * Altera o custo de compra do produto
	 *
	 * @param in         nome do produto
	 * @param novo_custo novo custo de compra
	 * @param novo_lucro novo lucro
	 * @throws Exception caso o produto nao seja encontrado ou o novo lucro seja
	 *                   invalido
	 */
	void alterar_custo(String in, double novo_custo, double novo_lucro) throws Exception {
		get_referencia_produto(in).setPreco(novo_custo * 1.18 * verifica_lucro(novo_lucro) / 100);
	}

	/**
	 * Altera a descricao do produto
	 *
	 * @param in       nome do produto
	 * @param novaDesc nova descricao
	 * @throws Exception caso o produto nao seja encontrado ou o novo nome seja
	 *                   invalido
	 */
	void alterar_descricao(String in, String nova_desc) throws Exception {
		if (nova_desc.length() < 3)
			throw new Exception("\n Nome invalido, menor que 3 caracteres");
		get_referencia_produto(in).setDesc(nova_desc);
	}

	/**
	 * Incrementa o total de vendas do produto
	 *
	 * @param in nome do produto
	 * @throws Exception caso o produto nao seja encontrado
	 */
	void baixa(String in, int vendido) throws Exception {
		try {
			get_referencia_produto(in).baixa(vendido);
		} catch (Exception e) {
			throw new Exception("\n Erro, nao ha " + in + " o suficiente");
		}
	}

	/**
	 * Incrementa o total de compras do produto
	 *
	 * @param in       nome do produto
	 * @param comprado quantidade comprada
	 * @throws Exception caso o produto nao seja encontrado
	 */
	void compra(String in, int comprado) throws Exception {
		get_referencia_produto(in).compra(comprado);
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
	 * @throws Exception caso o produto nao seja encontrado
	 */
	void listar(String in) throws Exception {
		get_referencia_produto(in).print_produto();
	}

	/**
	 * Printa as informacoes de todos os produtos
	 *
	 */
	void listar_geral() {
		for (Celula tmp = this.topo.anterior; tmp != null; tmp = tmp.anterior)
			tmp.item.print_produto();
	}
}
