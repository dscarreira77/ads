package view;
	
import java.io.IOException;
import java.util.Stack;

import controller.AbstractUseCaseController;
import domain.interfaces.IGoodPlaces;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;


public class MainView extends Application {

	public static final String SHOW_CATEGORIES = "ShowCategories.fxml";
	public static final String SHOW_INDICATORS = "ShowIndicators.fxml";
	public static final String CREATE_USER = "CreateUser.fxml";
	public static final String CREATE_UNIT = "CreateUnit.fxml";
	public static final String CREATE_CONTEXT = "CreateContext.fxml";
	public static final String CREATE_CONTEXTCATEGORY = "CreateContextCharacteristic.fxml";
	public static final String CREATE_CONTEXTRESP = "CreateContextResp.fxml";
	public static final String CREATE_CHARACTERISTIC = "CreateCharacteristic.fxml";
	public static final String LINK_MANAGER = "LinkManagerToContext.fxml";
	public static final String SET_CONFIG = "SetConfiguration.fxml";
	public static final String REGISTER_READING = "RegisterReading.fxml";

	private Stack<Pair<Pane, AbstractUseCaseController>> viewStack;
	private static IGoodPlaces app;
	private Stage primaryStage;
    private BorderPane rootLayout;
	private String currentCategory;
	private String currentIndicator;
	private boolean userLoggedIn;
	
	@Override
	public void start(Stage primaryStage) {
		viewStack = new Stack<>();
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GoodPlaces");
        initRootLayout();
        pushPane("Login.fxml");
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainView.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Give the controller access to the main app.
            AbstractUseCaseController controller = loader.getController();
            controller.setMainView(this);
            controller.setApp(app);  
            controller.init();

            // the root pane is the base of the stack and is never removed 
        	viewStack.push(new Pair<>((Pane)rootLayout.getCenter(), controller));

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	public static void setApp(IGoodPlaces dcoApp) {
		app = dcoApp;
	}

	public void setCurrentCategory(String selectedCategory) {
		currentCategory = selectedCategory;
	}
	
	public String getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentIndicator(String selectedIndicator) {
		currentIndicator = selectedIndicator;
	}

	public String getCurrentIndicator() {
		return currentIndicator;
	}

	public void showError(String errorHeader, String errorText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Constants.ERROR_DIALOG_TITLE);
		alert.setHeaderText(errorHeader);
		alert.setContentText(errorText);
		alert.showAndWait();
	}

	public void showInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Constants.INFO_DIALOG_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public AbstractUseCaseController pushPane(String paneFile) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainView.class.getResource(paneFile));
            AnchorPane pane = (AnchorPane) loader.load();
            
            // Give the controller access to the main app.
            AbstractUseCaseController controller = loader.getController();
        	controller.setMainView(this);
        	controller.setApp(app);  
            if (controller.preConditionVerified()) {
            	controller.init();

            	// Set person overview into the center of root layout.
            	rootLayout.setCenter(pane);

            	// push pane
            	viewStack.push(new Pair<>((Pane)rootLayout.getCenter(), controller));
            } else {
            	controller.preConditionError();
            }
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public void popPane() {
		viewStack.pop();
		Pair<Pane, AbstractUseCaseController> pc = viewStack.peek();
	    rootLayout.setCenter(pc.getKey());
	}
	
	public void popAndUpdate() {
		viewStack.pop();
		Pair<Pane, AbstractUseCaseController> pc = viewStack.peek(); 
		pc.getValue().update();
        rootLayout.setCenter(pc.getKey());
	}

	public boolean isUserLoggedIn() {
		return userLoggedIn;
	}
	
	public void userLoggedInSuccessfully() {
		userLoggedIn = true;
	}

	public void logOut() {
		userLoggedIn = false;
	}
}
