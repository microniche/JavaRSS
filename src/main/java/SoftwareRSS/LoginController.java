package SoftwareRSS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Aurélien on 08/01/2017.
 */
public class LoginController
{
    static final private String loginUrl = "";

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    static private Stage _loginStage;

    public LoginController(Stage primaryStage)
    {
        _loginStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginAlert.fxml"));
        loader.setController(this);
        try
        {
            GridPane alertPane = loader.load();
            _loginStage.setScene(new Scene(alertPane));
            _loginStage.initOwner(primaryStage);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // The connection should provide a token to authenticate

    @FXML
    private void login()
    {
        System.out.println("prout");
        if (true) // appel de sendLoginRequest ne retourne pas null
        {
            MainController.setToken("token");
            Main.mainController.updateLogTexts("Bienvenue " + "pseudo", "Logout");
        }
        _loginStage.hide();
    }

    static public void showLoginWindow()
    {
        _loginStage.show();
    }

    private String sendLoginRequest()
    {
        return (HttpHandler.sendPost(loginUrl, "username", usernameField.getText(), "password", passwordField.getText()));
    }
}
