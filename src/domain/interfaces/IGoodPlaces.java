package domain.interfaces;

/**
 * A interface com o objeto inicial da aplicacao
 * 
 * @author ADS
 *
 */
public interface IGoodPlaces {

	/**
	 * @return O handler do caso de uso Autenticar utilizador
	 */
	ILoginHandler obtemLoginHandler();

	/**
	 * @requires O user autenticado e' um Tecnico
	 * @return O handler do caso de uso Criar caracteristica ou
	 *         null no caso do user autenticado nao ser Tecnico
	 */
	ICriarCaracteristicaHandler obtemCriarCaracteristicaHandler();

	/**
	 * @requires O user autenticado e' um Tecnico
	 * @return O handler do caso de uso Recolher dados manualmente ou
	 *         null no caso do user autenticado nao ser Tecnico
	 */
	IRecolherDadosHandler obtemRecolherDadosHandler();

	/**
	 * @return O handler do caso de uso Logout
	 */
	ILogoutHandler obtemLogoutHandler();

	/**
	 * @return O tipo do utilizador autenticado
	 */
	String obtemTipoUserAutenticado();

}
