package domain.handlers;

import domain.*;
import domain.interfaces.ICriarCaracteristicaHandler;

public class CriarCaracteristicaHandler implements ICriarCaracteristicaHandler {

    private CatalogoDescricaoCaracteristicas catDescCaracteristicas;
    private CatalogoTiposSensor catTipoSensor;
    private CatalogoUnidades catUnidades;
    private DescricaoCaracteristica dc;

    public CriarCaracteristicaHandler(CatalogoDescricaoCaracteristicas catDescrCaract,
                                      CatalogoTiposSensor catTiposSensor, CatalogoUnidades catUnidades) {
        this.catDescCaracteristicas = catDescrCaract;
        this.catTipoSensor = catTiposSensor;
        this.catUnidades = catUnidades;
    }

    @Override
    public boolean criarCaracteristica(String desig) {
        if (!catDescCaracteristicas.existeCaracteristica(desig)) {
            dc = new DescricaoCaracteristica(desig);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> obterTiposSensor() {
        return catTipoSensor.nomesTiposSensor();
    }

    @Override
    public boolean indicarTipoSensor(String tipo) {
        TipoSensor ts = catTipoSensor.obtemTipoSensor(tipo);
        if(ts != null) {
            dc.associaTipoSensor(ts);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> obterUnidadesMedida() {
        return dc.obtemTipoSensor().obtemNomeUnidades();
    }

    @Override
    public boolean indicarUnidade(String abrev) {
        UnidadeMedida uni = catUnidades.obtemUnidade(abrev);
        if(uni != null) {
            dc.defineUnidadeMedida(uni);
            return true;
        }
        return false;
    }

    @Override
    public void confirmar() {
        catDescCaracteristicas.adicionaCaracteristica(dc);
    }

    @Override
    public void cancelar() {

    }
}
