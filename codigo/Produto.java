/**
 * Classe que representa um produto
 *
 * @author JPPauletti
 */
class Produto {
	private String desc;
	private double custo_compra, valor_venda;
	private int total_vendas, total_compra, minimo;

	/**
	 * Construtor da classe Produto, inicializa os atributos e verifica se os
	 * valores sao validos (desc.length() >= 3, lucro entre 30 e 80)
	 * 
	 * @param desc   descricao do produto
	 * @param custo  custo de compra do produto
	 * @param lucro  margem de lucro do produto
	 * @param minimo quantidade minima de produtos no estoque
	 * @throws IllegalArgumentException caso os valores sejam invalidos
	 */
	Produto(String desc, double custo, double lucro, int minimo) throws IllegalArgumentException {
		if (desc.length() < 3)
			throw new IllegalArgumentException("\n Nome invalido, menor que 3 caracteres");
		if (lucro < 30 || lucro > 80)
			throw new IllegalArgumentException("\n Margem de lucro invalida, apenas entre 30 e 80");
		if (minimo < 0 || custo < 0)
			throw new IllegalArgumentException("\n Valor invalido, menor que 0");
		this.desc = desc;
		this.custo_compra = custo;
		this.valor_venda = custo * (1 + lucro / 100) * 1.18; // 18% de imposto sobre o custo + lucro
		this.total_vendas = this.total_compra = 0;
		this.minimo = minimo;
	}

	/**
	 * @return descricao do produto
	 */
	String get_desc() {
		return this.desc;
	}

	/**
	 * @return valor de venda do produto
	 */
	double get_estoque() {
		return (this.total_compra - this.total_vendas) * this.valor_venda;
	}

	/**
	 * Verifica se ha produtos suficiente para o valor padrao
	 *
	 * @return true se ha produtos no estoque, false caso contrario
	 */
	boolean suficiente() {
		return suficiente(this.minimo);
	}

	/**
	 * Verifica se ha produtos o suficiente para uma venda, usado em
	 * {@link Produto#baixa(int)} e {@link Produto#suficiente()}
	 *
	 * @param min quantidade minima necessaria
	 * @return true se ha produtos o suficiente, false caso contrario
	 */
	private boolean suficiente(int min) {
		return this.total_compra - this.total_vendas >= min;
	}

	/**
	 * Incrementa o total de vendas do produto
	 *
	 * @param vendido quantidade vendida
	 * @throws IllegalStateException caso nao haja produtos o suficiente
	 */
	void baixa(int vendido) throws IllegalStateException {
		if (!suficiente(vendido))
			throw new IllegalStateException("\n Estoque insuficiente");
		this.total_vendas += vendido;
	}

	/**
	 * Incrementa o total de compras do produto
	 *
	 * @param comprado quantidade comprada
	 */
	void compra(int comprado) {
		this.total_compra += comprado;
	}

	/**
	 * Compra estoque minimo
	 * 
	 * @return quantidade comprada
	 */
	int compra_minima() {
		int diferenca = this.minimo - (this.total_compra - this.total_vendas);
		if (diferenca > 0)
			this.compra(diferenca);
		return diferenca;
	}

	/**
	 * Printa as informacoes do produto
	 */
	void print_produto() {
		System.out.format(
				"\n Nome: %s\n  Vendas: %d\n  Valor de venda: %.2f\n  Compras: %d\n  Custo: %.2f\n  Total em estoque: %d\n  Arrecadamento: %.2f\n  Valor do estoque: %.2f\n  Estoque minimo? %s",
				this.desc, this.total_vendas, this.valor_venda, this.total_compra, this.custo_compra,
				this.total_compra - this.total_vendas, // total em estoque
				this.total_vendas * this.valor_venda, // arrecadamento
				(this.total_compra - this.total_vendas) * this.valor_venda, // valor do estoque
				suficiente() ? "Sim" : "Nao" // estoque minimo
		);
	}

}
