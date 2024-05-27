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
        this.ctx = catContextos.obtemContexto(nome);
        System.out.println(ctx);
        if(ctx != null) {
            tecnico.ficasAssociado(ctx);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> obtemCaracteristicasUnidades() {
        List<String> list = new ArrayList<>();
        Iterable<Par<String, String>> pares =  ctx.obtemCaracteristicasUnidade();
        for (Par<String, String> par : pares) {
            list.add(par.primeiro() + "-" + par.segundo());
        }

        return list;
    }

    @Override
    public boolean indicarCaracteristica(String nome, String unidade) {
        return ctx.definirCaractUnidCorreta(nome, unidade);
    }

    @Override
    public void indicarLeitura(int ano, int mes, int dia, double valor) {
        ctx.registaLeitura(ano, mes, dia, valor);
    }

    @Override
    public void cancelar() {

    }
}
