package domain;

/**
 * Classe que define pares
 * @author ADS
 */
public class Par<T1, T2> {
	private T1 first;
	private T2 second;
	
	/**
	 * Construtor
	 * @param f - O primeiro elemento
	 * @param s - O segundo elemento
	 */
	public Par (T1 f, T2 s){
		first = f;
		second = s;
	}
	
	/**
	 * O primeiro elemento do par
	 */
	public T1 primeiro(){
		return first;
	}
	
	/**
	 * O segundo elemento do par
	 */
	public T2 segundo(){
		return second;
	}

}
