package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que define tipos de sensores
 * @author ADS
 */
public class TipoSensor {
	private String designacao;
	private UnidadeMedida unidade;
	private Map<String,Sensor> sensores;

	/**
	 * Construtor
	 * @param designacao - Designacao do novo tipo de sensor
	 * @param uni - Unidade de medida default para o novo tipo de sensor
	 * @param codigosSensores - Os codigos dos sensores deste novo tipo
	 * @requires designacao != null && uni != null && codigosSensores != null
	 */
	public TipoSensor(String designacao, UnidadeMedida uni, String[] codigosSensores) {
		this.designacao = designacao;
		unidade = uni;
		sensores = new HashMap<>();
		for(String s : codigosSensores) {
			sensores.put(s, new Sensor(s));
		}
	}

	/**
	 * A designacao deste tipo de sensor
	 * @return
	 */
	public String obtemDesignacao() {
		return designacao;
	}

	/**
	 * O sensor com um dado codigo que esta' associado a este tipo de sensor
	 * @param codigo - Codigo do sensor desejado
	 * @return A instancia de Sensor com o codigo indicado, que esta' associada
	 *         a este tipo de sensor
	 * @requires codigo != null
	 */
	public Sensor obtemSensor(String codigo) {
		return sensores.get(codigo);
	}

}
