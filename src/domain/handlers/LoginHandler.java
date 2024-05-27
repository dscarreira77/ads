package domain.handlers;

import domain.CatalogoUtilizadores;
import domain.Utilizador;
import domain.interfaces.ILoginHandler;
import services.SessionManager;

public class LoginHandler implements ILoginHandler {

    private final CatalogoUtilizadores catUser;

    public LoginHandler(CatalogoUtilizadores catUser) {
        this.catUser = catUser;
    }

    @Override
    public boolean login(String nome, String pwd) {
        Utilizador u = catUser.obtemUtilizador(nome);
        if (u != null && u.pwdCorreta(pwd)) {
            SessionManager.getInstance().createSession(nome);
            return true;
        }
        return false;
    }
}
