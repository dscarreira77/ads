package domain.interfaces;

/**
 * O interface que define as operacoes do sistema do caso de uso 
 * Recolha de dados manual
 * 
 * Este caso de uso so' pode ser executado quando o utilizador 
 * autenticado e' um Tecnico
 * 
 * @author ADS
 *
 */
public interface IRecolherDadosHandler {
    
	/**
	 * Inicia o caso de uso
	 * 
	 * @return lista de nomes dos contextos com os quais o
	 *         utilizador autenticado esta' associado
	 */
	Iterable<String> iniciarRecolha();

	/**
	 * Indica o contexto para o qual quer registar leituras
	 * 
	 * @param nome O nome do contexto
	 * @return true se o contexto com este nome existe e o tecnico autenticado
	 *              esta' associado a ele
	 */       
	boolean indicarContexto(String nome);

	/**
	 * @return lista de Strings caracteristica - unidade associados ao contexto
	 *         corrente
	 */
	Iterable<String> obtemCaracteristicasUnidades();

	/**
	 * Indica uma caracteristica e unidade para registar leituras
	 * 
	 * @param nome O nome da caracteristica
	 * @param abrev A abreviatura da unidade de medida em que foi feita a leitura
	 * @return true se :
	 *         - uma caracteristica com este nome existe no sistema e
	 *         - esta' associada ao contexto corrente e 
     *         - a unidade escolhida e' a unidade default da caracteristica
	 */
	boolean indicarCaracteristica(String nome, String unidade);

    /**
     * Regista uma nova leitura para a caracteristica corrente.
     * 
     * @param ano O ano da leitura
     * @param mes O mes da leitura (1-12)
     * @param dia O dia da leitura (1-31)
     * @param valor O valor observado
     */
    void indicarLeitura(int ano, int mes, int dia, double valor);

	/**
	 * Abandona o caso de uso
	 */
	void cancelar();
}
