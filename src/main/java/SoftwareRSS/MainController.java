package SoftwareRSS;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;

/**
 * Created by Aurélien on 08/01/2017.
 */
public class MainController
{
    @FXML
    private Label logLabel;

    @FXML
    private Button logButton;

    @FXML
    private ListView articlesView;

    private VBox layout = null;

    public MainController()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainWindow.fxml"));
        loader.setController(this);
        try
        {
            layout = loader.load();

            articlesView.setCellFactory(new Callback<ListView<Article>, ListCell>() {
                @Override
                public ListCell call(ListView param) {
                    return new CustomListCell();
                }
            });
            Article[] articleArray = new Article[]
                    {
                            new Article("titre nul", "description boaf", "link foireux"),
                            new Article("titre moyen", "description nulle", "link moisi"),
                            new Article("titre trop cool", "description amazing", "link qui pue")
                    };
            ObservableList testList = FXCollections.observableArrayList(articleArray);
            articlesView.setItems(new ObservableListWrapper(testList));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void loginOrLogout()
    {

    }

    public void updateLogTexts(String labelText, String buttonText)
    {
        logLabel.setText(labelText);
        logButton.setText(buttonText);
    }

    public VBox getLayout()
    {
        return (layout);
    }
}
