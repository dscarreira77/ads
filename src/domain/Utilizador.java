package domain;

import domain.alertas.AlertaAdapterFactory;
import domain.alertas.IAlertaAdapter;
import domain.alertas.IEventoAlerta;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Utilizador implements PropertyChangeListener {
	
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getNewValue() instanceof IEventoAlerta) {
			IAlertaAdapter adapter = AlertaAdapterFactory.getInstance().getAdapter();
			adapter.enviaAlerta(this, (IEventoAlerta) evt.getNewValue());
		}
	}

	public List<String> nomesContextoAssociado() {
		List<String> lista = new ArrayList<>();
		for(Contexto context : contextosAssociado.values()) {
			lista.add(context.obtemDesignacao());
		}
		return lista;
	}
}
