package domain;

/**
 * Classe que define intervalos de valores
 * @author ADS
 */
public class ValoresReferencia {
	
	private double max, min;
	
	/**
	 * Construtor
	 * @param minimo - Limite inferior do intervalo
	 * @param maximo - Limite superior do intervalo
	 */
	public ValoresReferencia (double minimo, double maximo) {
		max = maximo;
		min = minimo;
	}

	/**
	 * Um dado valor pertence a este intervalo?
	 * @param valor - O valor
	 * @return
	 */
	public int estahNoIntervalo(double valor) {
		int result = 0;
		if (valor < min)
			result = -1;
		else if (valor > max)
			result = 1;
		return result;
	}
}
