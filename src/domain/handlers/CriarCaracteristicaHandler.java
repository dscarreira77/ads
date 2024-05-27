package domain.handlers;

import domain.*;
import domain.interfaces.ICriarCaracteristicaHandler;

public class CriarCaracteristicaHandler implements ICriarCaracteristicaHandler {

    private CatalogoDescricaoCaracteristicas catDescCaracteristicas;
    private CatalogoTiposSensor catTipoSensor;
    private CatalogoUnidades catUnidades;
    private DescricaoCaracteristica dc;

    public CriarCaracteristicaHandler(CatalogoDescricaoCaracteristicas catDescrCaract, CatalogoTiposSensor catTiposSensor, CatalogoUnidades catUnidades) {
        this.catDescCaracteristicas = catDescrCaract;
        this.catTipoSensor = catTiposSensor;
        this.catUnidades = catUnidades;
    }

    @Override
    public boolean criarCaracteristica(String desig) {
        if (catDescCaracteristicas.existeCaracteristica(desig)) {
            return false;
        }
        dc = new DescricaoCaracteristica(desig);
        return true;
    }

    @Override
    public Iterable<String> obterTiposSensor() {
        return catTipoSensor.nomesTiposSensor();
    }

    @Override
    public boolean indicarTipoSensor(String tipo) {
        TipoSensor ts = catTipoSensor.obtemTipoSensor(tipo);
        if (ts == null) {
            return false;
        }
        dc.associaTipoSensor(ts);
        return true;
    }

    @Override
    public Iterable<String> obterUnidadesMedida() {
        return dc.obtemTipoSensor().obtemNomeUnidades();
    }

    @Override
    public boolean indicarUnidade(String abrev) {
        UnidadeMedida uni = catUnidades.obtemUnidade(abrev);
        if (uni == null) {
            return false;
        }
        dc.defineUnidadeMedida(uni);
        return true;
    }

    @Override
    public void confirmar() {
        if (dc != null) {
            catDescCaracteristicas.adicionaCaracteristica(dc);
            dc = null; // Reseta dc para evitar reutilização indevida
        }
    }

    @Override
    public void cancelar() {

    }
}
