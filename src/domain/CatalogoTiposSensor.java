package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que define catalogos de tipos de sensores
 * @author ADS
 */
public class CatalogoTiposSensor {
	private Map<String,TipoSensor> tiposSensor;
	
	/**
	 * Construtor
	 */
	public CatalogoTiposSensor(){
		tiposSensor = new HashMap<>();
	}
	
	/**
	 * Criar alguns tipos de sensor. So' para poder testar UC6 e UC11
	 * @param catUni - Catalogo de unidades
	 */
	public void loadSomeTipoSensor(CatalogoUnidades catUni) {
		// Cria 4 tipos de sensor, cada um com 3 sensores desse tipo
		UnidadeMedida uni = catUni.obtemUnidade("oC");
		this.adicionaTpSensor(new TipoSensor("Termometro", uni, 
				                         new String[]{"Ter01","Ter02","Ter03"}));

		uni = catUni.obtemUnidade("dB");
		this.adicionaTpSensor(new TipoSensor("Decibelimetro", uni, 
				                         new String[] {"Dec01","Dec02","Dec03"}));
		
		uni = catUni.obtemUnidade("QQ");
		this.adicionaTpSensor(new TipoSensor("Luximetro", uni, 
				                         new String[] {"Lux01","Lux02","Lux03"}));
		this.adicionaTpSensor(new TipoSensor("Barometro", uni, 
				                         new String[] {"Bar01","Bar02","Bar03"}));		
		
	}
 
	/**
	 * Adicionar um novo tipo de sensor a este catalogo
	 * @param tp - O tipo de sensor a adicionar
	 * @requires tp != null
	 */
	public void adicionaTpSensor(TipoSensor tp) {
		tiposSensor.put(tp.obtemDesignacao(), tp);		
	}

	/**
	 * Os nomes dos tipos de sensores existentes neste catalogo
	 * @return Uma colecao iteravel com os nomes dos tipos de sensores        
	 */
	public Iterable<String> nomesTiposSensor() {
		return tiposSensor.keySet();
	}

	/**
	 * O tipo de sensor que tem uma dada designacao
	 * @param desig - A designacao do tipo de sensor
	 * @return A instancia de TipoSensor correspondente a desig ou null se 
	 *         nao existe
	 * @requires desig != null
	 */
	public TipoSensor obtemTipoSensor(String desig) {
		return tiposSensor.get(desig);
	}	
}
