package loja;

public class Cupom {

    private final String codigo;
    private final double percentual;

    public Cupom(String codigo, double percentual) {
        this.codigo = codigo;
        this.percentual = percentual;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPercentual() {
        return percentual;
    }
}