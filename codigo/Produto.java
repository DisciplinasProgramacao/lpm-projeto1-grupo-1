/**
 * Classe que representa um produto
 *
 * @author JPPauletti
 */
public class Produto {
	private String desc;
	private double custo_compra, valor_venda;
	private int total_vendas, total_compra;

	/**
	 * Construtor da classe Produto, inicializa os atributos e verifica se os
	 * valores sao validos (desc.length() >= 3, lucro entre 30 e 80)
	 * 
	 * @param desc  descricao do produto
	 * @param custo custo de compra do produto
	 * @param lucro margem de lucro do produto
	 * @throws Exception caso os valores sejam invalidos
	 */
	Produto(String desc, double custo, double lucro) throws Exception {
		if (desc.length() < 3)
			throw new Exception("\n Nome invalido, menor que 3 caracteres");
		if (lucro < 30 || lucro > 80)
			throw new Exception("\n Margem de lucro invalida, apenas entre 30 e 80");
		this.desc = desc;
		this.custo_compra = custo;
		this.valor_venda = custo * 1.18 * lucro / 100;
		this.total_vendas = this.total_compra = 0;
	}

	/**
	 * @return descricao do produto
	 */
	String get_desc() {
		return this.desc;
	}

	/**
	 * Verifica se ha produtos o suficiente para a venda
	 *
	 * @param min quantidade minima necessaria
	 * @return true se ha produtos o suficiente, false caso contrario
	 */
	boolean suficiente(int min) {
		return this.total_compra - this.total_vendas >= min;
	}

	/**
	 * Incrementa o total de vendas do produto
	 *
	 * @param vendido quantidade vendida
	 * @throws Exception caso nao haja produtos o suficiente
	 */
	void baixa(int vendido) throws Exception {
		if (!suficiente(vendido))
			throw new Exception("\n Erro, nao ha produtos o suficiente");
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
	 * Printa as informacoes do produto
	 */
	void print_produto() {
		System.out.format(
				"\n Nome: %s\n  Vendas: %d\n  Valor de venda: %.2f\n  Compras: %d\n  Custo: %.2f\n  Total em estoque: %d\n  Arrecadamento: %.2f",
				this.desc, this.total_vendas, this.valor_venda, this.total_compra, this.custo_compra,
				this.total_compra - this.total_vendas, this.total_vendas * this.valor_venda);
	}
}
