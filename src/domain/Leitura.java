package domain;

public class Leitura {

    private double valor;
    private UnidadeMedida unidade;
    private Sensor sensor;

    public Leitura(double valor, UnidadeMedida unidade) {
        this.valor = valor;
        this.unidade = unidade;
    }
}
