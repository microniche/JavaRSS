package SoftwareRSS;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Aurélien on 08/01/2017.
 */
public class LoginController {

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    static private Stage _loginStage;

    public LoginController(Stage loginStage){
        _loginStage = loginStage;
    }

    @FXML
    public boolean login(){
        System.out.println("prout");
        _loginStage.hide();
        return (false);
    }

    static public void showLoginWindow(){
        _loginStage.show();
    }
}
