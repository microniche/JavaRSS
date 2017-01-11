package SoftwareRSS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

/**
 * Created by Aurélien on 10/01/2017.
 */
public class CustomArticleListCell extends ListCell<Article> {
    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label linkLabel;


    public CustomArticleListCell()
    {
        super();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("customArticleCell.fxml"));
        loader.setController(this);
        try
        {
            setGraphic(loader.load());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Article item, boolean empty)
    {
        super.updateItem(item, empty);
        if (!empty && item != null)
        {
            titleLabel.setText(item.getTitle());
            descriptionLabel.setText(item.getDescription());
            linkLabel.setText(item.getLink());
        }
    }
}
