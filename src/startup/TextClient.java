package startup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import domain.CatalogoUnidades;
import domain.GoodPlaces;
import domain.UnidadeMedida;
import domain.interfaces.ICriarCaracteristicaHandler;
import domain.interfaces.ILoginHandler;
import domain.interfaces.IRecolherDadosHandler;

/**
 * Classe que testa a execucao dos vários casos de uso da GoodPlaces
 * para os alunos poderem comparar os seus resultados
 * @author ADS
 *
 */
public class TextClient {
	// O objeto inicial
	private static GoodPlaces objIni;

	public static void main(String[] args) {

		iniciar();
		testaCasoUsoLogin("Um qualquer", "nao interessa");
		testaCasoUsoLogin("Tecnico1", "8u7y6t");
		
		// Um Tecnico autentica-se
		testaCasoUsoLogin("Tecnico1", "123");
		listaInfoUnidades();
		testaCasoUsoCriarCaracteristica(); // Criar caracteristica
			
		
		// Um gestor autentica-se
		testaCasoUsoLogin("Gestor1", "123");

		// Outro gestor autentica-se
		testaCasoUsoLogin("Gestor2", "abc");

		// Um tecnico autentica-se
		testaCasoUsoLogin("Tecnico2", "abc");
		testaCasoUsoRecolherDados1();  // Recolher dados
		testaCasoUsoRecolherDados2();  // Idem

	}

	/**
	 * Metodo para ver a informacao contida nas Unidades de Medida.
	 * Para isto ser possivel, deve existir um método public na classe 
	 * GoodPlaces que retorna o CatalogoUnidades
	 * Esse metodo nao devera' existir numa versao para comercializacao
	 */
	private static void listaInfoUnidades() {
	       
		System.out.println("============= INFORMACAO SOBRE UNIDADES =============");
        CatalogoUnidades uniCat = objIni.obtemCatalogoUnidades();
        for (String s : uniCat.nomesUnidades()) {
        	UnidadeMedida u = uniCat.obtemUnidade(s);
        	System.out.println(u.toString());
        }      
}

	/////////////////////////////////////////
	// STARTUP : CRIACAO DO OBJECTO INICIAL
	////////////////////////////////////////
	private static void iniciar() {
		// Criacao do objeto inicial
		objIni = new GoodPlaces();

		// Definicao do ficheiro de propriedades
		Properties prop = new Properties ();	    
		
		// Definicao do adapter para criacao de alertas
		// Os alunos devem experimentar com um e com o outro
		prop.setProperty("alertaAdapter", "SysOutAlertaAdapter");
//		prop.setProperty("alertaAdapter", "FileAlertaAdapter");
		
		// Guardar as propriedades num ficheiro para que a factory depois
		// o possa usar
		try {
			prop.store(new FileOutputStream("configuracao.properties"), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/////////////////////////////////////////
	// LOGIN DE UM UTILIZADOR
	////////////////////////////////////////
	private static void testaCasoUsoLogin(String nome, String pwd) {
		System.out.println("=============== LOGIN DE UTILIZADOR =================");

		ILoginHandler hand = objIni.obtemLoginHandler();
		boolean b = hand.login(nome, pwd);
		if (b) {
			System.out.println(nome + " autenticou-se com password " + pwd);
		}
		else {
			System.out.println("Utilizador " + nome + 
					" nao existe ou password " + pwd + " incorreta");
		}
	}
	
	/////////////////////////////////////////
	// CRIACAO DE CARACTERISTICAS
	////////////////////////////////////////
	private static void testaCasoUsoCriarCaracteristica() {
		System.out.println("=============== CRIAR CARACTERISTICAS =================");		

		// Obtem o controlador do caso de uso Criar Caracteristica
		ICriarCaracteristicaHandler hand = objIni.obtemCriarCaracteristicaHandler();
		
		// Criar primeira caracteristica, 
		hand.criarCaracteristica("Temperatura Extra");
		System.out.println("Criar caracteristica Temperatura Extra");        	
        Iterable<String> tiposSensor = hand.obterTiposSensor();
		System.out.println("Tipos de sensor existentes:");        	
        imprimeStrings(tiposSensor);
        boolean b = hand.indicarTipoSensor("Sabedoriometro");
        if (!b){
			System.out.println("Tipo sensor Sabedoriometro nao existe");        	
        }
		System.out.println("Escolhe finalmente Termometro");
        b = hand.indicarTipoSensor("Termometro");
        if (!b){
			System.out.println("Tipo sensor Termometro nao existe");        	
        }
        Iterable<String> unidades = hand.obterUnidadesMedida();
		System.out.println("Unidades de medida possiveis de se usar"
				+ " para Temperatura Extra:");        	
        imprimeStrings(unidades);
        b = hand.indicarUnidade("oo");
		if (!b){
			System.out.println("Unidade oo nao existe");
		}
		System.out.println("Escolhe finalmente oC");
        b = hand.indicarUnidade("oC");
		if (!b){
			System.out.println("Unidade oC nao existe");
		}
        hand.confirmar();
        
		// Criar segunda caracteristica
        System.out.println("==== CRIAR OUTRA CARACT ====");
        // Tentar primeiro criar uma ja' existente
        b = hand.criarCaracteristica("Temperatura Extra");
		if (!b){
			System.out.println("Temperatura Extra jah existe");
		}
        b = hand.criarCaracteristica("Luxuosidade");
		System.out.println("Criar caracteristica Luxuosidade");        	
		if (!b){
			System.out.println("Luxuosidade jah existe");
		}
        hand.indicarTipoSensor("Luximetro");
        unidades = hand.obterUnidadesMedida();
		System.out.println("Unidades de medida possiveis de se usar"
				+ " para Luxuosidade:");        	
        imprimeStrings(unidades);
        hand.indicarUnidade("QQ");
        hand.confirmar();		

	}
	
	/////////////////////////////////////////
	// RECOLHER DADOS MANUALMENTE 1
	////////////////////////////////////////
   private static void testaCasoUsoRecolherDados1() {
		System.out.println("=============== RECOLHER DADOS MANUALMENTE =================");

	    IRecolherDadosHandler hand = objIni.obtemRecolherDadosHandler();
	    
	    Iterable<String> contextos = hand.iniciarRecolha();
		System.out.println("Contextos aos quais o tecnico autenticado esta' associado:");        	
	    imprimeStrings(contextos);
 	    hand.indicarContexto("Sala");
	    Iterable<String> caractUni = hand.obtemCaracteristicasUnidades();
		System.out.println("Caracteristicas/Unidades existentes:");        	
	    imprimeStrings(caractUni);

	    System.out.println("==== LEITURAS PARA UMA CARACT ====");	    
    	System.out.println("Escolhe Caracteristica: Temperatura ; Unidade: oC");
	    boolean b = hand.indicarCaracteristica("Temperatura", "oC");
	    if (!b)
	    	System.out.println("Sala/Temperatura - algo correu mal!!");
	    hand.indicarLeitura(2024, 4, 29, 35);
	    hand.indicarLeitura(2024, 4, 30, 20);
	    hand.indicarLeitura(2024, 5, 1, 8);

	    System.out.println("==== LEITURAS PARA OUTRA CARACT ====");
    	System.out.println("Escolhe Caracteristica: Volume de Som ; Unidade: dB");
	    b = hand.indicarCaracteristica("Volume de Som", "dB");
	    if (!b)
	    	System.out.println("Sala/Volume de Som - algo correu mal!!");
	    hand.indicarLeitura(2024, 4, 26, 100);
	    hand.indicarLeitura(2024, 4, 27, 50);
	    hand.indicarLeitura(2024, 4, 28, -10);

	}

	/////////////////////////////////////////
	// RECOLHER DADOS MANUALMENTE 2
	////////////////////////////////////////
   private static void testaCasoUsoRecolherDados2() {
		System.out.println("=============== RECOLHER DADOS MANUALMENTE =================");

	    IRecolherDadosHandler hand = objIni.obtemRecolherDadosHandler();
	    
	    Iterable<String> contextos = hand.iniciarRecolha();
		System.out.println("Contextos aos quais o tecnico autenticado esta' associado:");        	
	    imprimeStrings(contextos);
	    hand.indicarContexto("Cozinha");
	    Iterable<String> caractUni = hand.obtemCaracteristicasUnidades();
		System.out.println("Caracteristicas/Unidades existentes:");        	
	    imprimeStrings(caractUni);

	    System.out.println("==== LEITURAS PARA A UNICA CARACT ====");	    
    	System.out.println("Escolhe Caracteristica: Temperatura ; Unidade: oC");
	    boolean b = hand.indicarCaracteristica("Temperatura", "oC");
	    if (!b)
	    	System.out.println("Cozinha/Temperatura - algo correu mal!!");
	    hand.indicarLeitura(2024, 5, 7, 23);
	    hand.indicarLeitura(2024, 5, 8, 15);
	    hand.indicarLeitura(2024, 5, 9, 2);

	}

	/**
	 * Imprimir uma colecao de strings no standard output
	 * @param l
	 */
	private static void imprimeStrings(Iterable<String> l){
		for(String s : l)
			System.out.println(s);
	}

}
