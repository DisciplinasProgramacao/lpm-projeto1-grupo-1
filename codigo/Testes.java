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
	Lista produtos;

	@BeforeEach
	void start() {
		produtos = new Lista();
	}

	/**
	 * Insere produtos para testes
	 * testa as funções {@link Lista#inserir(Produto)} e {@link Lista#vazia()}
	 */
	void inserirProdutosTeste() {
		produtos.inserir(new Produto("batata", 10, 50));
		produtos.inserir(new Produto("arroz", 8, 36));
		produtos.inserir(new Produto("feijao", 15, 75));
	}

	/**
	 * Espera-se que a lista recem criada esteja vazia
	 * testa a função {@link Lista#vazia()}
	 */
	@Test
	void vazia() {
		assertTrue(produtos.vazia());
	}

	/**
	 * Alguns testes de insercao de produtos e suas excecoes
	 * testa as funções {@link Lista#inserir(Produto)} e
	 * {@link Lista#get_cel(String)}
	 */
	@Test
	void inserir() {
		// Espera-se que um produto com lucro menor que 30 nao seja aceito
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.inserir(new Produto("arroz", 10, 29));
		});

		// Espera-se que um produto com lucro maior que 80 nao seja aceito
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.inserir(new Produto("arroz", 10, 81));
		});

		// Espera-se que um produto com descricao menor que 3 letras nao seja aceito
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.inserir(new Produto("ar", 10, 30));
		});

		// Espera-se que um produto valido possa ser inserido
		produtos.inserir(new Produto("arroz", 10, 50));

		// Espera-se que um produto não possa ser inserido duas vezes
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.inserir(new Produto("arroz", 10, 50));
		});
	}

	/**
	 * Alguns testes de compra e venda de produtos e suas excecoes
	 * testa as funções {@link Lista#compra(String, int)},
	 * {@link Lista#baixa(String, int)}, {@link Lista#inserir(Produto)} e
	 * {@link Lista#get_cel(String)}
	 */
	@Test
	void compraVendaInsercao() {
		// Espera-se que os seguintes produtos sejam validos
		inserirProdutosTeste();

		// Espera-se que a lista nao esteja vazia
		assertTrue(!produtos.vazia());

		// Espera-se que o produto com nome ja adicionado nao seja aceito
		assertThrows(IllegalArgumentException.class, () -> {
			produtos.inserir(new Produto("arroz", 10, 30));
		});

		// Espera-se que "arroz" nao possa ser vendido sem que seja comprado
		assertThrows(IllegalStateException.class, () -> {
			produtos.baixa("arroz", 10);
		});

		// Espera-se que "arroz" possa ser comprado e vendido
		produtos.compra("arroz", 10);
		produtos.baixa("arroz", 10);

		// Espera-se que "arroz" nao possa ser vendido (10 compras e 10 baixas)
		assertTrue(!produtos.get_produto("arroz").suficiente(1));

		// Espera-se que "arroz" possa ser vendido (11 compras e 10 baixas)
		produtos.compra("arroz", 1);
		assertTrue(produtos.get_produto("arroz").suficiente(1));
	}

	/**
	 * Alguns testes de listagem de produtos
	 * testa a função {@link Lista#total()}
	 */
	@Test
	void quantidadeDeProdutos() {
		// Insere 3 produtos
		inserirProdutosTeste();

		// Espera-se que o sistema retorne a quantidade correta de produtos inseridos, 3
		assertEquals(3, produtos.total());
	}

	/**
	 * Alguns testes de remocao de produtos
	 * testa a função {@link Lista#remover(String)}
	 */
	@Test
	void remocaoDeProdutos() {
		// Espera-se que um produto que não foi adicionado não possa ser removido
		assertThrows(NoSuchElementException.class, () -> {
			produtos.remover("produtoTeste");
		});

		// Insere um produto
		produtos.inserir(new Produto("batata", 10, 50));

		// Espera-se remover um produto existente
		assertEquals("batata", produtos.remover("batata"));
	}

	/**
	 * Valida o valor total do estoque
	 * testa a função {@link Lista#valor_total()}
	 */
	void valorTotal() {
		// Insere produtos
		inserirProdutosTeste();
		produtos.compra("batata", 10);
		produtos.compra("arroz", 10);
		produtos.compra("feijao", 10);

		// Valor esperado: 615.1
		assertEquals(615.1, produtos.valor_total());
	}

}
