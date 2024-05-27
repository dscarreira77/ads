package domain;

import java.util.HashMap;
import java.util.Map;

public abstract class Utilizador {
	
	private String nome, pwd, email;
	private Map<String,Contexto> contextosAssociado;

	public Utilizador (String nome) {
		contextosAssociado = new HashMap<>();
		this.nome = nome;		
	}

	public void definirPassword(String pwd2) {
		pwd = pwd2;		
	}

	public String obtemNome() {
		return nome;
	}
	
	public String obtemPassword() {
		return pwd;
	}

	public boolean pwdCorreta(String pwd2) {
       return pwd2.equals(pwd);
	}

	public void ficasAssociado(Contexto contCorr) {
		contextosAssociado.put(contCorr.obtemDesignacao(), contCorr);
		contCorr.addObserver(this);		
	}
}
