package controller;

import view.Constants;
import view.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RootLayoutController extends AbstractNotAuthenticatedUseCaseController {

	
	@FXML void createCharacteristic(ActionEvent event) {
		if ( app.obtemTipoUserAutenticado().equals("Gestor") ) {
			mainView.showError(Constants.CREATE_UNIT_PERMISSION, Constants.CREATE_UNIT_TECNICO);
			return;
		}
		mainView.pushPane(MainView.CREATE_CHARACTERISTIC);		
	}
	
	@FXML void setConfig(ActionEvent event) {
		mainView.pushPane(MainView.SET_CONFIG);		
	}
	
	@FXML void manualReading(ActionEvent event) {
		if ( app.obtemTipoUserAutenticado().equals("Gestor") ) {
			mainView.showError(Constants.CREATE_UNIT_PERMISSION, Constants.CREATE_UNIT_TECNICO);
			return;
		}
		mainView.pushPane(MainView.REGISTER_READING);		
	}
	
	@FXML void logout(ActionEvent event) {
		mainView.popPane();
		mainView.logOut();
		mainView.pushPane("Login.fxml");
	}
}

