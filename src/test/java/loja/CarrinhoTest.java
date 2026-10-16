package loja;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {

    @Test
    void carrinhoVazioTemTotalZero() {
        Carrinho carrinho = new Carrinho();
        assertEquals(0.0, carrinho.calcularTotal());
    }

    @Test
    void adicionarItemAumentaTotalPorPrecoVezesQuantidade() throws EstoqueInsuficienteException {
        Carrinho carrinho = new Carrinho();
        Produto camiseta = new Produto("Camiseta", 50.0, 10);

        carrinho.adicionar(camiseta, 2);

        assertEquals(100.0, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void naoPermiteAdicionarQuantidadeMaiorQueEstoque() {
        Carrinho carrinho = new Carrinho();
        Produto caneca = new Produto("Caneca", 30.0, 3);

        assertThrows(EstoqueInsuficienteException.class,
                () -> carrinho.adicionar(caneca, 5));
    }

    @Test
    void removerItemReduzTotal() throws EstoqueInsuficienteException {
        Carrinho carrinho = new Carrinho();
        Produto camiseta = new Produto("Camiseta", 50.0, 10);
        Produto caneca = new Produto("Caneca", 30.0, 3);
        carrinho.adicionar(camiseta, 2);
        carrinho.adicionar(caneca, 1);

        carrinho.remover(caneca);

        assertEquals(100.0, carrinho.calcularTotal(), 0.001);
    }
}