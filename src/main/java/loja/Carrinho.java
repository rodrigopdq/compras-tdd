package loja;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionar(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > produto.getEstoque()) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente para " + produto.getNome()
                    + ": disponivel " + produto.getEstoque()
                    + ", solicitado " + quantidade + ".");
        }
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::getSubtotal)
                .sum();
    }
}