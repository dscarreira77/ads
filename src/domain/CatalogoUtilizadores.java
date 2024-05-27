package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que define catalogos de utilizadores
 * @author ADS
 */
public class CatalogoUtilizadores {
	private Map<String, Utilizador> users;
	
	/**
	 * Construtor
	 */
	public CatalogoUtilizadores () {
		users = new HashMap<String,Utilizador>();
		loadSomeUsers();
	}
	
	/**
	 * Criar alguns utilizadores. So' para poder testar UC6 e UC11
	 */
	private void loadSomeUsers() {

		Utilizador utG = new Gestor("Gestor1");
		utG.definirPassword("123");
		this.adicionaUtilizador(utG);

		Utilizador utT = new Tecnico("Tecnico1");
		utT.definirPassword("123");
		this.adicionaUtilizador(utT);

		utG = new Gestor("Gestor2");
		utG.definirPassword("abc");
		this.adicionaUtilizador(utG);

		utT = new Tecnico("Tecnico2");
		utT.definirPassword("abc");
		this.adicionaUtilizador(utT);		
	}

	/**
	 * Adicionar um novo utilizador a este catalogo
	 * @param novoUser - O utilizador a adicionar
	 * @requires novoUser != null
	 */
	public void adicionaUtilizador(Utilizador novoUser) {
		users.put(novoUser.obtemNome(),novoUser);		
	}

	/**
	 * Obter um utilizador com um dado nome
	 * @param nome - O nome do utilizador
	 */
	public Utilizador obtemUtilizador(String nome) {
		return users.get(nome);
	}
}
