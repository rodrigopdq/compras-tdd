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

    public void aplicarCupom(Cupom novoCupom) throws CupomJaAplicadoException {
        if (cupomJaAplicado(novoCupom)) {
            throw new CupomJaAplicadoException(
                    "O cupom " + novoCupom.getCodigo() + " ja foi aplicado.");
        }
        this.cupom = novoCupom;
    }

    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        return subtotal - calcularDesconto(subtotal);
    }

    public void finalizarCompra() throws CarrinhoVazioException {
        if (itens.isEmpty()) {
            throw new CarrinhoVazioException("Nao e possivel finalizar a compra com o carrinho vazio.");
        }
    }

    private double calcularSubtotal() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::getSubtotal)
                .sum();
    }

    private double calcularDesconto(double subtotal) {
        if (cupom == null) {
            return 0.0;
        }
        return subtotal * cupom.getPercentual() / 100;
    }

    private boolean cupomJaAplicado(Cupom novoCupom) {
        return cupom != null && cupom.getCodigo().equals(novoCupom.getCodigo());
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