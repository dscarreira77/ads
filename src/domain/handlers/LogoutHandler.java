package domain.handlers;

import domain.interfaces.ILogoutHandler;
import services.SessionManager;

public class LogoutHandler implements ILogoutHandler {
    @Override
    public void logout() {
        SessionManager.getInstance().deleteSession();
    }
}
