public class Lista {
	private Celula topo;

	Lista() {
		this.topo = new Celula(null,null);
	}

	void inserir(Produto novo_item) throws Exception {
		if (get_referencia_cel(novo_item.getDesc()) != null)
			throw new Exception("\n Erro, produto ja existente");
		this.topo.anterior = new Celula(novo_item, this.topo.anterior);
	}

	void listar() {
		for (Celula tmp = this.topo.anterior; tmp != null; tmp = tmp.anterior)
			tmp.item.print_produto();
	}
}
