package domain.alertas;

public interface IEventoAlerta {
    int ano();
    int mes();
    int dia();
    double valor();
    String unidade();
    String caracteristica();
    String contexto();
    String mensagem();
    void adicionaContexto(String contexto);
}
