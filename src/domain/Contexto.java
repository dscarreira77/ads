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
	private Map<String, CaracteristicaContexto> carcons;
	private CaracteristicaContexto caracteristicaAtual;
	private List<Leitura> leituras;
	private PropertyChangeSupport support;

	public Contexto(String designacao) {
		this.designacao = designacao;
		this.carcons = new HashMap<>();
		this.leituras = new ArrayList<>();
		this.support = new PropertyChangeSupport(this);
	}

	public void adicionaCaracteristica(CaracteristicaContexto caracteristica) {
		carcons.put(caracteristica.nomeCaracteristica(), caracteristica);
		caracteristica.addObserver(this);
	}

	public String obtemDesignacao() {
		return this.designacao;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getNewValue() instanceof IEventoAlerta) {
			IEventoAlerta alerta = (IEventoAlerta) evt.getNewValue();
			alerta.adicionaContexto(this.designacao); // Adicionando a designação do contexto ao evento
			support.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), alerta); // Notificando os observadores com o evento alterado
		}
	}

	public boolean definirCaractUnidCorreta(String nome, String unidade) {
		CaracteristicaContexto caracteristica = carcons.get(nome);
		if (caracteristica != null) {
			this.caracteristicaAtual = caracteristica;
			return caracteristica.definirUnidadeCorreta().equals(unidade);
		}
		return false;
	}

	public Iterable<Par<String, String>> obtemCaracteristicasUnidade() {
		List<Par<String, String>> lista = new ArrayList<>();
		for (CaracteristicaContexto caracteristica : carcons.values()) {
			String nome = caracteristica.nomeCaracteristica();
			String abreviatura = caracteristica.abreviaturaUnidade();
			lista.add(new Par<>(nome, abreviatura));
		}
		return lista;
	}

	public void registaLeitura(double valor) {
		if (caracteristicaAtual != null) {
			UnidadeMedida unidade = caracteristicaAtual.getDescricao().obtemUnidadeMedida();
			leituras.add(new Leitura(valor, unidade));
			int intervalo = caracteristicaAtual.getValoresReferencia().estahNoIntervalo(valor);
			if (intervalo != 0) {
				IEventoAlerta alerta = criarAlerta(unidade);
				support.firePropertyChange("Alerta", null, alerta);
			}
		}
	}

	private IEventoAlerta criarAlerta(UnidadeMedida unidade) {
		return new IEventoAlerta() {
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
				return caracteristicaAtual.nomeCaracteristica();
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
				// TODO: Implementar método
			}
		};
	}

	public void addObserver(Utilizador utilizador) {
		this.support.addPropertyChangeListener(utilizador);
	}
}
