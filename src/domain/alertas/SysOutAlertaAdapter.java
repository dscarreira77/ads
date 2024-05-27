package domain.alertas;

import domain.Utilizador;

public class SysOutAlertaAdapter implements IAlertaAdapter {
    @Override
    public void enviaAlerta(Utilizador u, IEventoAlerta ev) {
        System.out.println("TODO: Escrever o alerta");
    }
}
