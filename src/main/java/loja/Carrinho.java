package loja;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private final List<ItemCarrinho> itens = new ArrayList<>();
    private Cupom cupom;

    public void adicionar(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        validarEstoque(produto, quantidade);
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public void remover(Produto produto) {
        itens.removeIf(item -> item.getProduto() == produto);
    }

    public void aplicarCupom(Cupom cupom) throws CupomJaAplicadoException {
        if (this.cupom != null && this.cupom.getCodigo().equals(cupom.getCodigo())) {
            throw new CupomJaAplicadoException(
                    "O cupom " + cupom.getCodigo() + " ja foi aplicado.");
        }
        this.cupom = cupom;
    }

    public double calcularTotal() {
        double subtotal = itens.stream()
                .mapToDouble(ItemCarrinho::getSubtotal)
                .sum();
        if (cupom != null) {
            subtotal -= subtotal * cupom.getPercentual() / 100;
        }
        return subtotal;
    }

    public void finalizarCompra() throws CarrinhoVazioException {
        if (itens.isEmpty()) {
            throw new CarrinhoVazioException("Nao e possivel finalizar a compra com o carrinho vazio.");
        }
    }

    private void validarEstoque(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > produto.getEstoque()) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente para " + produto.getNome()
                    + ": disponivel " + produto.getEstoque()
                    + ", solicitado " + quantidade + ".");
        }
    }
}