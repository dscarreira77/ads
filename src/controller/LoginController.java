package controller;

import view.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends AbstractNotAuthenticatedUseCaseController {

	@FXML private TextField usernameField;
	@FXML private TextField passwordField;

	private String errorMessage;

    /**
     * Called when the user clicks the SignIn button.
     */
    @FXML
    private void handleSignIn() {
    	if (isInputValid()) {
        	if (app.obtemLoginHandler().login(usernameField.getText(), passwordField.getText())) {
        		mainView.userLoggedInSuccessfully();
        		mainView.popPane();
        		mainView.pushPane("Welcome.fxml");
        	} else {
        		mainView.showError(Constants.LOGIN_FAIL_TO_LOGIN, Constants.LOGIN_RECOVERY);
        	}
    	} else {
        	mainView.showError(errorMessage, Constants.LOGIN_RECOVERY);
        }
    }
        
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        errorMessage = "";

        if (usernameField.getText() == null || usernameField.getText().length() == 0)  {
            errorMessage += Constants.LOGIN_INVALID_USERNAME + "\n"; 
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) { 
            errorMessage += Constants.LOGIN_INVALID_PASSWORD + "\n"; 
        }

        return errorMessage.length() == 0;
    }
}
