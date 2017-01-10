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

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

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

    @FXML
    private boolean login()
    {
        System.out.println("prout");
        _loginStage.hide();
        Main.mainController.updateLogTexts("wesh gros", "prout");
        return (false);
    }

    static public void showLoginWindow()
    {
        _loginStage.show();
    }
}
