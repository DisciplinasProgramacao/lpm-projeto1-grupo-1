public class Celula {
	Produto item;
	Celula anterior;

	Celula(Produto item, Celula anterior) {
		this.item = item;
		this.anterior = anterior;
	}
}
