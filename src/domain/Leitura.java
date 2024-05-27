package domain;

import java.time.LocalDateTime;

public class Leitura {

    private double valor;
    private UnidadeMedida unidade;
    private LocalDateTime date;

    public Leitura(int ano, int mes, int dia, double valor, UnidadeMedida unidade) {
        this.date = LocalDateTime.of(ano, mes, dia, 0, 0);
        this.valor = valor;
        this.unidade = unidade;
    }
}
