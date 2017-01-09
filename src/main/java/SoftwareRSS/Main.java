package SoftwareRSS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainWindow.fxml"));
        loader.setController(new MainController());
        VBox root = loader.load();

        Stage loginStage = new Stage();
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginAlert.fxml"));
        loader.setController(new LoginController(loginStage));
        GridPane alertPane = loader.load();
        loginStage.setScene(new Scene(alertPane));
        loginStage.initOwner(primaryStage);

        primaryStage.setTitle("Software RSS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LoginController.showLoginWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
