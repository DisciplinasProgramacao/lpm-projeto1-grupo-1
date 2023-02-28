/**
 * Classe que auxilia a lista de produtos
 *
 * @author Henrique Almeida
 */
public class Celula {
	Produto item;
	Celula anterior;

	/**
	 * Construtor da classe que cria a celula sentinela
	 */
	Celula(Produto item, Celula anterior) {
		this.item = item;
		this.anterior = anterior;
	}
}
