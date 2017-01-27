package SoftwareRSS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by Aurélien on 08/01/2017.
 */
public class LoginController
{
    static final private String loginUrl = "http://localhost:8080/ServeurJavaRSS/login";
    static final private String logoutUrl = "http://localhost:8080/ServeurJavaRSS/logout";
    static final private String registerUrl = "http://localhost:8080/ServeurJavaRSS/register";

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberCheckBox;

    static private Stage _loginStage;
    static final private String credentialsFilePath = "credentials";
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
        if (sendLoginRequest().equals("ok")) // appel de sendLoginRequest ne retourne pas null
        {
            if (HttpHandler.previousSetCookie != null)
                HttpHandler.cookiesToProvide = HttpHandler.previousSetCookie;
            Main.mainController.updateLogTexts("Bienvenue " + usernameField.getText(), "Logout");
            if (rememberCheckBox.isSelected())
                FileHandler.clearAndWriteLines(credentialsFilePath, usernameField.getText(), passwordField.getText());
            else
                FileHandler.removeFile(credentialsFilePath);
            usernameField.clear();
            passwordField.clear();
            Main.mainController.updateRssFeeds();
            Main.mainController.updateArticles();
            _loginStage.hide(); // we hide the window only if the connection succeed
            checkLogged();
        }
    }

    public void fillFields(String username, String password)
    {
        usernameField.setText(username);
        passwordField.setText(password);
        rememberCheckBox.setSelected(true);
    }

    static public void showLoginWindow()
    {
        List<String> credentials = FileHandler.readLines(credentialsFilePath);
        if (credentials != null)
        {
            if (credentials.size() != 2) //invalid file
                FileHandler.removeFile(credentialsFilePath);
            else
                Main.loginController.fillFields(credentials.get(0), credentials.get(1));
        }
        _loginStage.show();
    }

    private String sendLoginRequest()
    {
        return (HttpHandler.sendPost(loginUrl, "mail", usernameField.getText(), "password", passwordField.getText()));
    }

    static public String sendLogoutRequest()
    {
        return (HttpHandler.sendGet(logoutUrl));
    }
    private void checkLogged()
    {
        System.out.println("Return of check logged : " + HttpHandler.sendGet("http://localhost:8080/ServeurJavaRSS/check"));
    }

    @FXML
    public void showRegisterPage()
    {
        Main.hostServices.showDocument(registerUrl);
    }
}
