package domain;

public class DescricaoCaracteristica {

    private String desig;
    private TipoSensor sensor;
    private UnidadeMedida medida;

    public DescricaoCaracteristica(String desig) {
        this.desig = desig;
    }

    public void associaTipoSensor(TipoSensor ts) {
        this.sensor = ts;
    }

    public void defineUnidadeMedida(UnidadeMedida uni) {
        this.medida = uni;
    }

    public String obtemDesignacao() {
        return desig;
    }

    public TipoSensor obtemTipoSensor() {
        return sensor;
    }

    public UnidadeMedida obtemUnidadeMedida() {
        return medida;
    }

    public String obtemAbreviaturaUnidade() {
        return medida.obtemAbreviatura();
    }
}
