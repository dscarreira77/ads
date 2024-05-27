package domain.interfaces;

/**
 * O interface que define as operacoes do sistema do caso de uso 
 * Autenticar utilizador
 * 
 * @author minunes
 *
 */
public interface ILoginHandler {

	/**
	 * @param nome O username do utilizador que se quer autenticar
	 * @param pwd A password associada com o username
	 * @return true quando o utilizador se autentica com sucesso 
	 */
	boolean login (String nome, String pwd);
}
