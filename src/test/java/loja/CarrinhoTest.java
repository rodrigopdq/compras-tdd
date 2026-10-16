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
    void adicionarItemAumentaTotalPorPrecoVezesQuantidade() {
        Carrinho carrinho = new Carrinho();
        Produto camiseta = new Produto("Camiseta", 50.0, 10);

        carrinho.adicionar(camiseta, 2);

        assertEquals(100.0, carrinho.calcularTotal(), 0.001);
    }
}