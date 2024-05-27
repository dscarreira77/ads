package startup;

import domain.interfaces.IGoodPlaces;
import domain.GoodPlaces;


/**
 * Executa o caso de uso Startup
 * 
 * @author ADS
 *
 */
public class StartUp {
    
	/**
	 * Let's try it
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		IGoodPlaces app = new GoodPlaces();
		new view.StartUp(app);
	}

}
