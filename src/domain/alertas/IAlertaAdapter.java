package domain.alertas;

import domain.Utilizador;

public interface IAlertaAdapter {
    void enviaAlerta(Utilizador u, IEventoAlerta ev);
}
