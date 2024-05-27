package controller;

import java.util.Calendar;

import domain.interfaces.IRecolherDadosHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import view.Constants;

public class RegisterReadingController extends AbstractAuthenticatedUseCaseController {

    @FXML private  ComboBox<String> contextField;
    
    @FXML private ComboBox<String> characteristicField;
    @FXML private TextField readingField;
    
    @FXML private TableView<ConfigurationLine> configsTable;
    @FXML private TableColumn<ConfigurationLine, String> characteristicColumn;
    @FXML private TableColumn<ConfigurationLine, String> readingColumn;
    @FXML private TableColumn<ConfigurationLine, String> unitColumn;


    private ObservableList<ConfigurationLine> data;

	private static class ConfigurationLine {
		private StringProperty characteristic;
		private StringProperty unit;
		private StringProperty reading;

		public ConfigurationLine(String cha, String unit, String reading) {
			this.characteristic = new SimpleStringProperty(cha);
			this.unit = new SimpleStringProperty(unit);
			this.reading = new SimpleStringProperty(reading);
		}
		

		public StringProperty characteristicProperty() {
			return characteristic;
		}
		
		public StringProperty unitProperty() {
			return unit;
		}
		
		public StringProperty readingProperty() {
			return reading;
		}

	}
	
	private IRecolherDadosHandler cuh;

	@Override
	public void init() {
		// execute the super class init
		super.init();
		
		// get the indicators handler from the application main class
		cuh = app.obtemRecolherDadosHandler();

		data = FXCollections.observableArrayList();
		characteristicColumn.setCellValueFactory(cellData -> cellData.getValue().characteristicProperty());
		unitColumn.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
		readingColumn.setCellValueFactory(cellData -> cellData.getValue().readingProperty());
		configsTable.setItems(data);
		
		Iterable<String> contexts = cuh.iniciarRecolha();
		contextField.getItems().clear();
		for (String ctx : contexts) {
			contextField.getItems().add(ctx);
		}
		contextField.setValue(null);
	}

    @FXML
    void cancel(ActionEvent event) {
		cuh.cancelar();	
		mainView.popPane();
    }

    @FXML
    void confirm(ActionEvent event) {
		mainView.showInfo(Constants.REGISTER_READING_SUCCESS);
		mainView.popAndUpdate();
    }

   @FXML
   void contextChanged(ActionEvent event) {
	   if (cuh.indicarContexto(contextField.getValue())) {
		   contextField.setDisable(true);
		   characteristicField.setDisable(false);
		   readingField.setDisable(false);
		   
			Iterable<String> characteristics = cuh.obtemCaracteristicasUnidades();
			characteristicField.getItems().clear();
			for (String c : characteristics) { 
				System.out.println("Characterists:" + c);
				characteristicField.getItems().add(c);
			}
			characteristicField.setValue(null);
		   
	   } else {
		   mainView.showError(Constants.REGISTER_READING_INVALID_CONTEXT, 
   				Constants.REGISTER_READING_RECOVERY);
	   }
   }
   
   @FXML
   void addConfig(ActionEvent event) {
	   if (characteristicField.getValue() == null) {
		   mainView.showError(Constants.REGISTER_READING_INVALID_CHARACTERISTIC, 
	   				Constants.REGISTER_READING_RECOVERY);
		   return;
	   }
   		String[] cha = characteristicField.getValue().split(" - ");
   		try {
	   		if (cha != null && cuh.indicarCaracteristica(cha[0], cha[1])) {
	   			String reading = readingField.getText();
	   			data.add(new ConfigurationLine(cha[0], cha[1], reading));
	   			Calendar c = Calendar.getInstance();
	   			cuh.indicarLeitura(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 
	   					c.get(Calendar.DAY_OF_MONTH), Double.parseDouble(reading));
	   		} else {
	 		   mainView.showError(Constants.REGISTER_READING_INVALID_CHARACTERISTIC, 
	 	   				Constants.REGISTER_READING_RECOVERY);
	   		}
   		} catch (NumberFormatException e ){
   			mainView.showError(Constants.REGISTER_READING_INVALID_CHARACTERISTIC, 
 	   				Constants.REGISTER_READING_RECOVERY);
   		}
   }
}

