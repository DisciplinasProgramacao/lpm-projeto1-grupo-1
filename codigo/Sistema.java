import java.util.NoSuchElementException;

/**
 * Classe que representa uma tablea hash de produtos
 *
 * @author Henrique Almeida
 */
class Sistema {
	private Produto[] produtos;

	/**
	 * Construtor da classe que cria um array de produtos
	 * 
	 * @param tamanho tamanho da tabela hash
	 */
	Sistema(int tamanho) {
		this.produtos = new Produto[tamanho];
	}

	/**
	 * Funcao hash para encontrar o index do produto no array, usada em
	 * {@link Sistema#get_produto(String)}, {@link Sistema#cadastrar(Produto)} e
	 * {@link Sistema#remover(String)}
	 * 
	 * @param in descricao do produto
	 * @return index do produto na tabela hash
	 */
	private int hash(String in) {
		int out = 0;
		for (int i = 0; i < in.length(); i++)
			out = (out * 7 + in.charAt(i)) % this.produtos.length;
		return out;
	}

	/**
	 * @param in descricao do produto
	 * @return o produto com a descricao dada
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 */
	Produto get_produto(String in) throws NoSuchElementException {
		int index = hash(in), i = 0; // funcao hash e contador
		while (this.produtos[index] != null && !this.produtos[index].get_desc().equals(in)
				&& i < this.produtos.length) {
			index = (index + 1) % this.produtos.length;
			i++;
		}
		if (this.produtos[index] == null || i == this.produtos.length) // nao existe
			throw new NoSuchElementException("\n Este produto nao esta cadastrado");
		return this.produtos[index]; // sucesso
	}

	/**
	 * Insere um novo produto na tabela hash
	 *
	 * @param novo_item nome do novo produto
	 * @throws IllegalArgumentException       caso o produto ja exista
	 * @throws ArrayIndexOutOfBoundsException caso a tabela esteja cheia
	 */
	void cadastrar(Produto novo_item) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
		int index = hash(novo_item.get_desc()), i = 0; // funcao hash e contador
		while (this.produtos[index] != null && !this.produtos[index].get_desc().equals(novo_item.get_desc())
				&& i < this.produtos.length) {
			index = (index + 1) % this.produtos.length;
			i++;
		}
		if (this.produtos[index] != null) // ja existe
			throw new IllegalArgumentException("\n Produto" + this.produtos[index].get_desc() + " ja cadastrado");
		if (i == this.produtos.length) // tabela cheia
			throw new ArrayIndexOutOfBoundsException("\n Tabela cheia, remova um produto antes de cadastrar um novo");
		this.produtos[index] = novo_item; // sucesso
	}

	/**
	 * Remove um produto da tabela hash e reorganiza elementos que anteriormente
	 * colidiram
	 *
	 * @param in nome do produto
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 */
	String remover(String in) throws NoSuchElementException {
		int index = hash(in), i = 0; // funcao hash e contador
		while (this.produtos[index] != null && !this.produtos[index].get_desc().equals(in)
				&& i < this.produtos.length) {
			index = (index + 1) % this.produtos.length;
			i++;
		}
		if (this.produtos[index] == null || i == this.produtos.length) // nao existe
			throw new NoSuchElementException("\n Este produto nao esta cadastrado");
		String out = this.produtos[index].get_desc(); // nome do produto removido
		this.produtos[index] = null;
		// reorganiza a tabela
		index = (index + 1) % this.produtos.length; // proximo elemento
		while (this.produtos[index] != null) { // reorganiza
			Produto temp = this.produtos[index];
			this.produtos[index] = null;
			cadastrar(temp);
			index = (index + 1) % this.produtos.length;
		}
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
	 * @param in nome do produto
	 * @throws NoSuchElementException caso o produto nao seja encontrado
	 */
	void compra(String in, int comprado) throws NoSuchElementException {
		get_produto(in).compra(comprado);
	}

	/**
	 * Realiza as compras de todos os produtos abaixo do minimo
	 * 
	 * @return o total de produtos comprados
	 */
	int repor_estoque() {
		int out = 0;
		for (Produto p : this.produtos)
			if (p != null)
				out += p.compra_minima();
		return out;
	}

	/**
	 * lista o produto com a descricao dada
	 */
	void listar_produto(String nome) {
		get_produto(nome).print_produto();
	}

	/**
	 * lista todos os produtos cadastrados
	 */
	void listar_geral() {
		int i = 0;
		for (Produto p : this.produtos)
			if (p != null) {
				System.out.print("\n" + i++ + " -");
				p.print_produto();
			}
	}

	/**
	 * @return true se nao ha produtos cadastrados, false caso contrario
	 */
	boolean vazia() {
		for (Produto p : this.produtos)
			if (p != null)
				return false;
		return true;
	}

	/**
	 * @return total de produtos cadastrados
	 */
	int total() {
		int total = 0;
		for (Produto p : this.produtos)
			if (p != null)
				total++;
		return total;
	}

	/**
	 * @return valor total do estoque
	 */
	double valor_total() {
		double total = 0;
		for (Produto p : this.produtos)
			if (p != null)
				total += p.get_estoque();
		return total;
	}

	/**
	 * lista todos os produtos abaixo do minimo
	 * 
	 * @return o total de produtos abaixo do minimo
	 */
	int listar_nminimo() {
		int out = 0;
		for (Produto p : this.produtos)
			if (p != null && !p.suficiente()) {
				p.print_produto();
				out++;
			}
		return out;
	}

}
