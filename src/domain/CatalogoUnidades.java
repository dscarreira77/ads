package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que define catalogos de unidades
 * @author ADS
 */
public class CatalogoUnidades {
	private Map<String,UnidadeMedida> unidades;
	
	/**
	 * Construtor
	 */
	public CatalogoUnidades(){
		unidades = new HashMap<>();
		loadSomeUnits();
	}

	/**
	 * Criar quatro unidades. So' para poder testar UC6 e UC11
	 * @param catUni - Catalogo de unidades
	 */	
	private void loadSomeUnits() {

		UnidadeMedida uniM = new UnidadeMedida("Grau Centigrado", "oC");
		this.acrescentaUnidade(uniM);

		uniM = new UnidadeMedida("Grau Fahrenheit", "oF");
		this.acrescentaUnidade(uniM);

		uniM = new UnidadeMedida("Decibel", "dB");
		this.acrescentaUnidade(uniM);

		uniM = new UnidadeMedida("Qualquer", "QQ");
		this.acrescentaUnidade(uniM);
		
	}

	/**
	 * Os nomes das unidades existentes neste catalogo
	 * @return Uma colecao iteravel com os nomes das unidades        
	 */
	public Iterable<String> nomesUnidades() {
		return unidades.keySet();		
	}

	/**
	 * Existe uma dada unidade?
	 * @param abrev - A abreviatura da unidade
	 * @requires abrev != null
	 */
	public boolean existeUnidade(String abrev) {
		return unidades.containsKey(abrev);
	}

	/**
	 * Obter uma unidade com uma dada abreviatura
	 * @param abrev - A abreviatura da unidade
	 * @requires existeUnidade(abrev)
	 */
	public UnidadeMedida obtemUnidade(String abrev) {
		return unidades.get(abrev);
	}

	/**
	 * Adicionar uma nova unidade a este catalogo
	 * @param u - A unidade a adicionar
	 * @requires u != null
	 */
	public void acrescentaUnidade(UnidadeMedida u) {
		unidades.put(u.obtemAbreviatura(), u);
		
	}

}
