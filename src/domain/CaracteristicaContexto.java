package domain;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicaContexto {

    private DescricaoCaracteristica desc;
    private List<Sensor> sensores;
    private ValoresReferencia referencia;
    private PropertyChangeSupport support;

    public CaracteristicaContexto(DescricaoCaracteristica carac) {
        this.desc = carac;
        this.sensores = new ArrayList<>();
        this.support = new PropertyChangeSupport(this);
    }

    public void registarLeitura() {
        //TODO
    }

    public String definirUnidadeCorreta() {
        return desc.obtemAbreviaturaUnidade();
    }

    public String nomeCaracteristica() {
        return desc.obtemDesignacao();
    }

    public String abreviaturaUnidade() {
        return desc.obtemAbreviaturaUnidade();
    }

    public DescricaoCaracteristica getDescricao() {
        return desc;
    }

    public void associaSensor(Sensor sens) {
        this.sensores.add(sens);
    }

    public void registaValoresRef(int min, int max) {
        this.referencia = new ValoresReferencia(min, max);
    }

    public ValoresReferencia getValoresReferencia() {
        return referencia;
    }

    public void addObserver(Contexto contexto) {
        this.support.addPropertyChangeListener(contexto);
    }
}
