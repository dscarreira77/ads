package domain;

/**
 * Classe que define sensores
 * @author ADS
 */
public class Sensor {
	private String codigo;
	
	/**
	 * Construtor
	 * @param cod - O nome de codigo do novo sensor
	 */
	public Sensor (String cod){
		codigo = cod;
	}
	
	/**
	 * O nome de codigo deste sensor
	 */
	public String obtemCodigo() {
		return codigo;
	}

}
