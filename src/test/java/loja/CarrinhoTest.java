package loja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {

    private Carrinho carrinho;
    private Produto camiseta;
    private Produto caneca;

    @BeforeEach
    void prepararCenario() {
        carrinho = new Carrinho();
        camiseta = new Produto("Camiseta", 50.0, 10);
        caneca = new Produto("Caneca", 30.0, 3);
    }

    @Test
    void carrinhoVazioTemTotalZero() {
        assertEquals(0.0, carrinho.calcularTotal());
    }

    @Test
    void adicionarItemAumentaTotalPorPrecoVezesQuantidade() throws EstoqueInsuficienteException {
        carrinho.adicionar(camiseta, 2);

        assertEquals(100.0, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void naoPermiteAdicionarQuantidadeMaiorQueEstoque() {
        assertThrows(EstoqueInsuficienteException.class,
                () -> carrinho.adicionar(caneca, 5));
    }

    @Test
    void removerItemReduzTotal() throws EstoqueInsuficienteException {
        carrinho.adicionar(camiseta, 2);
        carrinho.adicionar(caneca, 1);

        carrinho.remover(caneca);

        assertEquals(100.0, carrinho.calcularTotal(), 0.001);
    }

    @Test
    void cupomValidoReduzTotalPeloPercentual() throws EstoqueInsuficienteException {
        carrinho.adicionar(camiseta, 2);
        Cupom cupom = new Cupom("DESCONTO10", 10.0);

        carrinho.aplicarCupom(cupom);

        assertEquals(90.0, carrinho.calcularTotal(), 0.001);
    }
}