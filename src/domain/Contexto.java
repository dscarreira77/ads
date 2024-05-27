package domain;

import domain.alertas.IEventoAlerta;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contexto implements PropertyChangeListener {
	
	private String designacao;
	private Map<String,CaracteristicaContexto> carcons;
    // ...
	private CaracteristicaContexto current;
	private List<Leitura> leituras;

	private PropertyChangeSupport support;

	public Contexto(String desig) {
		carcons = new HashMap<>();
		designacao = desig;
		this.leituras = new ArrayList<>();
		support = new PropertyChangeSupport(this);
		// ...
	}

	public void adicionaCaracteristica(CaracteristicaContexto carCont) {
		carcons.put(carCont.nomeCaracteristica(), carCont);
		carCont.addObserver(this);
	}

	public String obtemDesignacao() {
		return designacao;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getNewValue() instanceof IEventoAlerta) {
			IEventoAlerta alerta = (IEventoAlerta) evt.getNewValue();
			alerta.adicionaContexto(designacao);
			//TODO Noficar pessoas
		}
	}

	public boolean definirCaractUnidCorreta(String nome, String unidade) {
		current = carcons.get(nome);
		if(current != null) {
			return current.definirUnidadeCorreta().equals(unidade);
		}
		return false;
	}

	public Iterable<Par<String, String>> obtemCaracteristicasUnidade() {
		List<Par<String, String>> list = new ArrayList<>();
		for(CaracteristicaContexto cc : carcons.values()) {
			String design = cc.nomeCaracteristica();
			String uni = cc.abreviaturaUnidade();
			list.add(new Par<>(design, uni));
		}
		return list;
	}

	public void registaLeitura(int ano, int mes, int dia, double valor) {
		UnidadeMedida unidade = current.getDescricao().obtemUnidadeMedida();
		leituras.add(new Leitura(ano, mes, dia, valor, unidade));
		int k = current.getValoresReferencia().estahNoIntervalo(valor); //??? valores maximos
		if(k != 0) {
			//TODO Fire property Change
			IEventoAlerta alert = new IEventoAlerta() {
				@Override
				public int ano() {
					return 0;
				}

				@Override
				public int mes() {
					return 0;
				}

				@Override
				public int dia() {
					return 0;
				}

				@Override
				public double valor() {
					return 0;
				}

				@Override
				public String unidade() {
					return unidade.obtemAbreviatura();
				}

				@Override
				public String caracteristica() {
					return current.nomeCaracteristica();
				}

				@Override
				public String contexto() {
					return designacao;
				}

				@Override
				public String mensagem() {
					return "Mensagem?";
				}

				@Override
				public void adicionaContexto(String contexto) {
					//TODO
				}
			};
			support.firePropertyChange("Alerta", null, alert);
		}
	}

	public void addObserver(Utilizador utilizador) {
		this.support.addPropertyChangeListener(utilizador);
	}
}
