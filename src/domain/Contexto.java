package domain;

import java.util.HashMap;
import java.util.Map;

public class Contexto {
	
	private String designacao;
	private Map<String,CaracteristicaContexto> carcons;
    // ...

	public Contexto(String desig) {
		carcons = new HashMap<>();
		designacao = desig;
		// ...
	}

	public void adicionaCaracteristica(CaracteristicaContexto carCont) {
		carcons.put(carCont.nomeCaracteristica(), carCont);
		carCont.addObserver(this);
	}

	public String obtemDesignacao() {
		return designacao;
	}
}
