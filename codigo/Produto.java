//JPPauletti

public class Produto {
	private String desc;
	private double custo_compra;
	private double valor_venda;
	private int total_vendas;
	private int total_compra;

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

	boolean suficiente(int min) {
		return this.total_compra - this.total_vendas >= min;
	}

	void baixa(int vendido) throws Exception {
		if (!suficiente(vendido))
			throw new Exception("\n Erro, nao ha produtos o suficiente");
		this.total_vendas += vendido;
	}

	void compra(int comprado) {
		this.total_compra += comprado;
	}

	void print_produto() {
		System.out.format(
				"\n Nome: %s\n  Vendas: %d\n  Valor de venda: %.2f\n  Compras: %d\n  Custo: %.2f\n  Total em estoque: %d",
				this.desc, this.total_vendas, this.valor_venda, this.total_compra, this.custo_compra,
				this.total_compra - this.total_vendas);
	}

	void setPreco(double valor_venda) {
		this.valor_venda = valor_venda;
	}

	void setDesc(String desc) {
		this.desc = desc;
	}

	String getDesc() {
		return this.desc;
	}

	double getCusto() {
		return this.custo_compra;
	}
}
