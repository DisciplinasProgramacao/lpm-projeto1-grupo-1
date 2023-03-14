import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de testes
 *
 * @author dolabelag, Igor Pinheiro
 */
class Testes {

	// Teste classe Produtos
	Produto produtoClass= new Produto("batata", 10, 50, 6);

	@Test
	void testRequisicaoDeDescricao() {
		assertEquals("batata", produtoClass.get_desc());
	}

	@Test
	void testRequisicaoDeEstoque() {
		produtoClass.compra(10);
		produtoClass.baixa(5);
		assertEquals(88.50, produtoClass.get_estoque());
	}

	// Teste classe Sistema

	Sistema produtos;

	@BeforeEach
	void start() {
		produtos = new Sistema(11);
	}

	/**
	 * Espera-se que a lista recem criada esteja vazia
	 * testa a funcao {@link Sistema#vazia()}
	 */
	@Test
	void testVazia() {
		assertTrue(produtos.vazia());
	}

	/**
	 * Alguns testes de insercao de produtos e suas excecoes
	 * testa as funcoes {@link Sistema#cadastrar(Produto)} e
	 * {@link Sistema#get_cel(String)}
	 */

	@Test
	void testaLucroMin() { // Espera-se que um produto com lucro menor que 30 nao seja aceito
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.cadastrar(new Produto("arroz", 10, 29, 0));
		});
	}

	@Test
	void testaLucroMax() { // Espera-se que um produto com lucro maior que 80 nao seja aceito
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.cadastrar(new Produto("arroz", 10, 81, 0));
		});
	}

	@Test
	void testaDescMin() { // Espera-se que um produto com descricao menor que 3 letras nao seja aceito
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.cadastrar(new Produto("ar", 10, 30, 0));
		});
	}

	@Test
	void testaInsercaoDuplicada() {
		produtos.cadastrar(new Produto("arroz", 10, 50, 0));
		// Espera-se que um produto nao possa ser inserido duas vezes
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.cadastrar(new Produto("arroz", 10, 50, 0));
		});
	}

	/**
	 * Insere produtos para testes
	 * testa as funcoes {@link Sistema#cadastrar(Produto)} e {@link Sistema#vazia()}
	 */
	@Test
	void insereProdutos() { // Espera-se que produtos validos possam ser comprados
		produtos.cadastrar(new Produto("arroz", 10, 50, 0));
		produtos.cadastrar(new Produto("feijao", 20, 50, 1));
		produtos.cadastrar(new Produto("batata", 30, 50, 2));
	}

	/**
	 * Alguns testes de compra e venda de produtos e suas excecoes
	 * testa as funcoes {@link Sistema#compra(String, int)},
	 * {@link Sistema#baixa(String, int)}, {@link Sistema#cadastrar(Produto)} e
	 * {@link Sistema#get_cel(String)}
	 */

	@Test
	void testaVendaSemCompra() { // Insere produtos para teste
		insereProdutos(); // Espera-se que "arroz" nao possa ser vendido sem que seja comprado
		assertThrows(IllegalStateException.class, () -> {
			produtos.baixa("arroz", 10);
		});
	}

	@Test
	void testaCompraVendaDeItemComprado() { // Insere produtos para teste
		insereProdutos();
		// Espera-se que "arroz" possa ser comprado e vendido
		produtos.compra("arroz", 10);
		produtos.baixa("arroz", 10);
	}

	/**
	 * Alguns testes de listagem de produtos
	 * testa a funcao {@link Sistema#total()}
	 */
	@Test
	void quantidadeDeProdutos() { // Insere 3 produtos para teste
		insereProdutos();
		// Espera-se que o sistema retorne a quantidade correta de produtos inseridos, 3
		assertEquals(3, produtos.total());
	}

	/**
	 * Alguns testes de remocao de produtos
	 * testa a funcao {@link Sistema#remover(String)}
	 */
	@Test
	void remocaoDeProdutosNaoInserido() { // Insere produtos para teste
		insereProdutos(); // Espera-se que um produto que nao foi adicionado nao possa ser removido
		assertThrows(NoSuchElementException.class, () -> {
			produtos.remover("produtoTeste");
		});
	}

	@Test
	void testaRemocaoDeProdutoExistente() {
		// Insere um produto
		produtos.cadastrar(new Produto("batata", 10, 50, 0));
		// Espera-se remover um produto existente
		assertEquals("batata", produtos.remover("batata"));
	}

	/**
	 * Valida o valor total do estoque
	 * testa a funcao {@link Sistema#valor_total()}
	 */
	void testaValorTotal() { // Insere produtos
		insereProdutos(); // Compra produtos
		produtos.compra("batata", 10);
		produtos.compra("arroz", 10);
		produtos.compra("feijao", 10);
		// Valor esperado: 615.1
		assertEquals(1062, produtos.valor_total());
	}

}