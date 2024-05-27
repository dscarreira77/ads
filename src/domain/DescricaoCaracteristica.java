package domain;

public class DescricaoCaracteristica {

    private String desig;
    private TipoSensor sensor;
    private UnidadeMedida medida;

    public DescricaoCaracteristica(String desig) {
        this.desig = desig;
    }

    public String obtemDesignacao() {
        return desig;
    }
    public void associaTipoSensor(TipoSensor ts) {
        this.sensor = ts;
    }

    public TipoSensor obtemTipoSensor() {
        return sensor;
    }

    public void defineUnidadeMedida(UnidadeMedida uni) {
        this.medida = uni;
    }

    public String obtemAbreviaturaUnidade() {
        return medida.obtemAbreviatura();
    }

    public UnidadeMedida obtemUnidadeMedida() {
        return medida;
    }

}
