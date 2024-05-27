package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que define catalogos de caracteristicas
 * @author ADS
 */
public class CatalogoDescricaoCaracteristicas {
	private Map<String,DescricaoCaracteristica> caracts;
	
	/**
	 * Construtor
	 */
	public CatalogoDescricaoCaracteristicas(){
		caracts = new HashMap<>();
	}
	
	/**
	 * Criar duas caracteristicas. So' existe para poder testar UC6 e UC11
	 * @param catSens - Catalogo de tipos de sensores
	 * @param catUni - Catalogo de unidades
	 */
	public void loadSomeDescCar(CatalogoTiposSensor catSens, CatalogoUnidades catUni) {
		// Cria uma primeira caracteristica
		DescricaoCaracteristica caract = new DescricaoCaracteristica("Temperatura");
		TipoSensor ts = catSens.obtemTipoSensor("Termometro");
		caract.associaTipoSensor(ts);
		UnidadeMedida uni = catUni.obtemUnidade("oC");
		caract.defineUnidadeMedida(uni);
		this.adicionaCaracteristica(caract);
		
		// Cria uma segunda caracteristica
		caract = new DescricaoCaracteristica("Volume de Som");
		ts = catSens.obtemTipoSensor("Decibelimetro");
		caract.associaTipoSensor(ts);
		uni = catUni.obtemUnidade("dB");
		caract.defineUnidadeMedida(uni);
		this.adicionaCaracteristica(caract);
		
	}

	/**
	 * Existe uma dada caracteristica?
	 * @param desig - A designacao da caracteristica
	 * @requires desig != null
	 */
	public boolean existeCaracteristica(String desig) {
		return caracts.containsKey(desig);
	}

	/**
	 * Adicionar uma nova caracteristica a este catalogo
	 * @param car - A caracteristica a adicionar
	 * @requires car != null
	 */
	public void adicionaCaracteristica(DescricaoCaracteristica car) {
		caracts.put(car.obtemDesignacao(), car);		
	}

	/**
	 * Os nomes das caracteristicas existentes neste catalogo
	 * @return Uma colecao iteravel com os nomes das caracteristicas        
	 */
	public Iterable<String> obtemNomesCaracteristicas() {
		return caracts.keySet();
	}

	/**
	 * Obter uma caracteristica com uma dada designacao
	 * @param desig - A designacao da caracteristica
	 * @requires existeCaracteristica(desig)
	 */
	public DescricaoCaracteristica obtemCaracteristica(String desig) {
		return caracts.get(desig);
	}


}
