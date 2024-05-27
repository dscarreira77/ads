package domain.interfaces;

/**
 * O interface que define as operacoes do sistema do caso de uso 
 * Criar caracteristica
 * Este caso de uso só pode ser executado quando o utilizador autenticado
 * e' um Tecnico
 *
 * @author ADS
 */
public interface ICriarCaracteristicaHandler {

	/**
	 * Inicia o caso de uso; indica o nome da nova caracteristica
	 * 
	 * @param desig A designacao para a nova caracteristica
	 * @return true se nao existe nenhuma caracteristica com esta designacao
	 */
	boolean criarCaracteristica(String desig);

	/**
	 * @return Os nomes de todos os tipos de sensores no sistema
	 */
	Iterable<String> obterTiposSensor();

	/**
	 * Indica o tipo de sensor que mede esta caracteristica 
	 * 
	 * @param tipo O nome do tipo de sensor
	 * @return true se o tipo de sensor existe no sistema
	 */
	boolean indicarTipoSensor(String tipo);

	/**
	 * @return Os nomes das unidades de medida compativeis com o
	 *         tipo de sensor indicado na operacao anterior
	 * Nesta fase do projeto nao consideramos unidades compativeis,
	 * por isso esta lista so' tera' um elemento
	 */
    Iterable<String> obterUnidadesMedida();

    /** 
	 * Indica a unidade de medida a ser usada por defeito na medicao dos 
	 * valores para esta nova caracteristica
	 * 
	 * @param abrev A abreviatura da unidade
	 * @return true se a unidade existe no sistema
	 */
	boolean indicarUnidade(String abrev);

	/**
	 * Confirma a criacao da caracteristica
	 */
	void confirmar();

	/**
	 * Abandona o caso de uso
	 */
	void cancelar();
}
