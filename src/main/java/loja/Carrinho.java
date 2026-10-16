package loja;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionar(Produto produto, int quantidade) {
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::getSubtotal)
                .sum();
    }
}