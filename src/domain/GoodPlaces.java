package domain;


import domain.handlers.CriarCaracteristicaHandler;
import domain.handlers.LoginHandler;
import domain.handlers.LogoutHandler;
import domain.handlers.RecolherDadosHandler;
import domain.interfaces.ICriarCaracteristicaHandler;
import domain.interfaces.IGoodPlaces;
import domain.interfaces.ILoginHandler;
import domain.interfaces.ILogoutHandler;
import domain.interfaces.IRecolherDadosHandler;
import services.SessionManager;

/**
 * Classe que define o objeto inicial da aplicacao
 * 
 * @author ADS
 */
public class GoodPlaces implements IGoodPlaces {
	private CatalogoDescricaoCaracteristicas catDescrCaract;
	private CatalogoContextos catContexts;
	private CatalogoUtilizadores catUser;
	private CatalogoTiposSensor catTiposSensor;
	private CatalogoUnidades catUnidades;

    /**
     * Construtor
     */
	public GoodPlaces() {
    	catUser = new CatalogoUtilizadores();       // Ja' sao criados alguns utilizadore
    	catUnidades = new CatalogoUnidades();       // Ja' sao criadas algumas unidades
    	catTiposSensor = new CatalogoTiposSensor();
    	catDescrCaract = new CatalogoDescricaoCaracteristicas();
    	catContexts = new CatalogoContextos();
    	// Criar alguns objetos necessarios para testar os casos de uso UC6
    	// e UC11 porque os outros casos de uso não estao ainda implementados:
    	catTiposSensor.loadSomeTipoSensor(catUnidades); // Cria 4 Tipos de sensor e sensores associados
    	catDescrCaract.loadSomeDescCar(catTiposSensor, catUnidades);  // Cria 2 caracteristicas
    	catContexts.loadSomeContexts(catDescrCaract, catUser);   // Cria dois contextos
    }

	@Override
	public ILoginHandler obtemLoginHandler() {
		return new LoginHandler(catUser);
	}

	@Override
	public ICriarCaracteristicaHandler obtemCriarCaracteristicaHandler() {
		Utilizador u = getAuthenticatedUser();
		if(u instanceof Tecnico) {
			return new CriarCaracteristicaHandler(catDescrCaract, catTiposSensor, catUnidades);
		}
		return null;
	}

	@Override
	public IRecolherDadosHandler obtemRecolherDadosHandler() {
		Utilizador t = getAuthenticatedUser();
		return new RecolherDadosHandler(t, catContexts);
	}

	@Override
	public ILogoutHandler obtemLogoutHandler() {
		return new LogoutHandler();
	}

	@Override
	public String obtemTipoUserAutenticado() {
		return getAuthenticatedUser().getClass().getName().substring(7);
	}

	private Utilizador getAuthenticatedUser() {
		 return catUser.obtemUtilizador(SessionManager.getInstance().getAuthenticatedUser());
	}

	public CatalogoUnidades obtemCatalogoUnidades() {
		return catUnidades;
	}

}
