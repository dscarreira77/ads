package domain;

public class UnidadeMedida {

	private String designacao;
	private String abreviatura;
	
	public UnidadeMedida(String nome, String abrev) {
		designacao = nome;
		abreviatura = abrev;
	}

	public String obtemAbreviatura() {
		return abreviatura;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(designacao + "     " + abreviatura + "\n");
		return sb.toString();
	}
}
