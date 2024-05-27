package view;

import domain.interfaces.IGoodPlaces;
import javafx.application.Application;

public class StartUp {
	
	public StartUp(IGoodPlaces app) {

		MainView.setApp(app);
		
		Application.launch(MainView.class);
	}
}
