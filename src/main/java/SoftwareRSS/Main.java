package SoftwareRSS;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    static public MainController mainController;
    static public LoginController loginController;
    static public HostServices hostServices;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //System.out.println(Utils.cutUrl("http://www.exemple.com:80/chemin/vers/monfichier.html?clé1=valeur1&clé2=valeur2#QuelquePartDansLeDocument"));
        //System.out.println(Utils.cutUrl("http://www.01net.com/rss/info/flux-rss/flux-toutes-les-actualites/"));
        hostServices = getHostServices();
        mainController = new MainController();
        loginController = new LoginController(primaryStage);
        VBox root = mainController.getLayout();

        primaryStage.setTitle("Software RSS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LoginController.showLoginWindow();
        System.out.println(HttpHandler.sendPost("http://localhost/BruteForceMyMind/web/app_dev.php/login", "nickname", "test", "password", "testtest"));
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
