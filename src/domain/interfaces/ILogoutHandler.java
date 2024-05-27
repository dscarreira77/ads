package domain.interfaces;

/**
 * O interface que define as operacoes do sistema do caso de uso 
 * Logout
 * 
 * @author minunes
 *
 */
public interface ILogoutHandler {

	/**
	 * Ends the session for the current authenticated user.
	 */
	void logout();

}