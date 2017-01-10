package SoftwareRSS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    static public MainController mainController;
    static public LoginController loginController;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainController = new MainController();
        loginController = new LoginController(primaryStage);
        VBox root = mainController.getLayout();

        primaryStage.setTitle("Software RSS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LoginController.showLoginWindow();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
