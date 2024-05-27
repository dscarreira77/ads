package domain.handlers;

import domain.*;
import domain.interfaces.IRecolherDadosHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecolherDadosHandler implements IRecolherDadosHandler {

    private Utilizador tecnico;
    private CatalogoContextos catContextos;
    private Contexto ctx;

    public RecolherDadosHandler(Utilizador t, CatalogoContextos catContextos) {
        this.tecnico = t;
        this.catContextos = catContextos;
    }

    @Override
    public Iterable<String> iniciarRecolha() {
        return tecnico.nomesContextoAssociado();
    }

    @Override
    public boolean indicarContexto(String nome) {
        Contexto contexto = catContextos.obtemContexto(nome);
        if (contexto == null) {
            return false;
        }
        this.ctx = contexto;
        tecnico.ficasAssociado(ctx);
        return true;
    }

    @Override
    public Iterable<String> obtemCaracteristicasUnidades() {
        if (ctx == null) {
            return new ArrayList<>();
        }
        List<String> caracteristicasUnidades = new ArrayList<>();
        for (Par<String, String> par : ctx.obtemCaracteristicasUnidade()) {
            caracteristicasUnidades.add(par.primeiro() + "-" + par.segundo());
        }
        return caracteristicasUnidades;
    }

    @Override
    public boolean indicarCaracteristica(String nome, String unidade) {
        return ctx.definirCaractUnidCorreta(nome, unidade);
    }

    @Override
    public void indicarLeitura(int ano, int mes, int dia, double valor) {
        if (ctx != null) {
            ctx.registaLeitura(valor);
        }
    }

    @Override
    public void cancelar() {
        ctx = null;
    }
}
