package controller;

import domain.interfaces.ICriarCaracteristicaHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import view.Constants;

public class CreateCharacteristicController extends AbstractAuthenticatedUseCaseController {

	@FXML private TextField nameField;
	@FXML private ComboBox<String> sensorTypeField;
	@FXML private ComboBox<String> unitField;
	private ICriarCaracteristicaHandler cch;
	
	@Override
	public void init() {
		cch = app.obtemCriarCaracteristicaHandler();
	}
	
    @FXML 
    private void cancel() {
    	mainView.popPane();
    }
    
    @FXML
    private void create() {
       	if (!isInputValid()) {
       		mainView.showError(Constants.CREATE_CHARACTERISTIC_INVALID_NAME, Constants.CREATE_CHARACTERISTIC_RECOVERY);
       		return;
       	}
       	
       	if (!nameField.isDisable()) {
       		if (cch.criarCaracteristica(nameField.getText())) {
       			nameField.setDisable(true);
       			sensorTypeField.getItems().clear();
       			for (String sensorT : cch.obterTiposSensor()) {
       				sensorTypeField.getItems().add(sensorT);
       			}
       			sensorTypeField.setDisable(false);
       			sensorTypeField.setValue(null);
       		} else {
       			mainView.showError(Constants.CREATE_CHARACTERISTIC_ERROR, Constants.CREATE_CHARACTERISTIC_RECOVERY);
       		}
   			return;
       	}
       	
       	if (!sensorTypeField.isDisable()) {
       		if (cch.indicarTipoSensor(sensorTypeField.getValue())) {
       			sensorTypeField.setDisable(true);
       			unitField.getItems().clear();
       			for (String sensorT : cch.obterUnidadesMedida()) {
       				unitField.getItems().add(sensorT);
       			}
       			unitField.setDisable(false);
       			unitField.setValue(null);
       		} else {
       			mainView.showError(Constants.CREATE_CHARACTERISTIC_ERROR, Constants.CREATE_CHARACTERISTIC_RECOVERY);
       		}
   			return;
       	}
       	
       	if (!unitField.isDisable()) {
       		if (cch.indicarUnidade(unitField.getValue())) {
       			unitField.setDisable(true);
       		} else {
       			mainView.showError(Constants.CREATE_CHARACTERISTIC_ERROR, Constants.CREATE_CHARACTERISTIC_RECOVERY);
       		}
   			return;
       	}	
    }
    
    @FXML
    private void confirm() {
    	cch.confirmar();
    	mainView.showInfo(Constants.CREATE_CHARACTERISTIC_SUCCESS);
	    mainView.popAndUpdate();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        return checkTextField(nameField);
    }
    
    private static boolean checkTextField(TextField f) {
    	return !(f.getText() == null || f.getText().trim().length() == 0);
    }
}
