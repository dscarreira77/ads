package domain.alertas;

import domain.Utilizador;

public class FileAlertaAdapter implements IAlertaAdapter {
    @Override
    public void enviaAlerta(Utilizador u, IEventoAlerta ev) {
        System.out.println("Enviando Alerta");
    }
}
