package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que define catalogos de contextos
 * @author ADS
 */
public class CatalogoContextos {
	private Map<String,Contexto> contextos;
	
	/**
	 * Construtor
	 */
	public CatalogoContextos(){
		contextos = new HashMap<>();
	}

	/**
	 * Criar dois contextos para poder testar UC6 e UC11
	 * @param catCars - Catalogo de descricao de caracteristicas
	 * @param catUsers - Catalogo de utilizadores
	 */
	public void loadSomeContexts(CatalogoDescricaoCaracteristicas catCars, 
			                     CatalogoUtilizadores catUsers) {
		// Cria um contexto
		Contexto cont = new Contexto("Sala");
		// Adiciona-lhe uma caracteristica
		DescricaoCaracteristica carac = catCars.obtemCaracteristica("Temperatura");
		CaracteristicaContexto carCont = new CaracteristicaContexto(carac);		
		TipoSensor tsCorr = carac.obtemTipoSensor();
	    Sensor sens = tsCorr.obtemSensor("Ter01");
	    carCont.associaSensor(sens);
	    carCont.registaValoresRef(18, 22);
	    cont.adicionaCaracteristica(carCont);
		// Adiciona-lhe outra caracteristica
		carac = catCars.obtemCaracteristica("Volume de Som");
		carCont = new CaracteristicaContexto(carac);		
		tsCorr = carac.obtemTipoSensor();
	    sens = tsCorr.obtemSensor("Dec01");
	    carCont.associaSensor(sens);
	    carCont.registaValoresRef(0, 70);
	    cont.adicionaCaracteristica(carCont);
	    
	    // Associa este contexto a dois tecnicos e um gestor
	    Utilizador usr = catUsers.obtemUtilizador("Gestor1");
	    usr.ficasAssociado(cont);
	    usr = catUsers.obtemUtilizador("Tecnico1");
	    usr.ficasAssociado(cont);
	    usr = catUsers.obtemUtilizador("Tecnico2");
	    usr.ficasAssociado(cont);
	    
	    this.adicionaContexto(cont);
	    
		// Cria outro contexto
		cont = new Contexto("Cozinha");
		// Adiciona-lhe uma caracteristica
		carac = catCars.obtemCaracteristica("Temperatura");
		carCont = new CaracteristicaContexto(carac);		
		tsCorr = carac.obtemTipoSensor();
	    sens = tsCorr.obtemSensor("Ter03");
	    carCont.associaSensor(sens);
	    carCont.registaValoresRef(12, 18);
	    cont.adicionaCaracteristica(carCont);
	    
	    // Associa este contexto a um tecnico
	    usr = catUsers.obtemUtilizador("Tecnico2");
	    usr.ficasAssociado(cont);
	    
	    this.adicionaContexto(cont);
	}

	/**
	 * Existe um dado contexto?
	 * @param desig - A designacao do contexto
	 * @requires desig != null
	 */
	public boolean existeContexto(String desig) {
		return contextos.containsKey(desig);
	}

	/**
	 * Adicionar um novo contexto a este catalogo
	 * @param cont - O contexto a adicionar
	 * @requires cont != null
	 */
	public void adicionaContexto(Contexto cont) {
		contextos.put(cont.obtemDesignacao(), cont);		
	}

	/**
	 * Obter um contexto com uma dada designacao
	 * @param desig - A designacao do contexto
	 * @requires existeContexto(desig)
	 */
	public Contexto obtemContexto(String desig) {
		return contextos.get(desig);
	}

}
