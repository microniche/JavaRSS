package SoftwareRSS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Created by Aurélien on 10/01/2017.
 */
public class CustomRssFeedListCell extends ListCell<RssFeed> {
    @FXML
    private Label titleLabel;

    @FXML
    private ImageView iconView;

    public CustomRssFeedListCell()
    {
        super();
        System.out.println("custom cell created");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("customRssFeedCell.fxml"));
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

    private void removeFeed(RssFeed feed)
    {
        //inform API to remove the feed
        Main.mainController.removeOneFeed(feed);
    }

    @Override
    public void updateItem(RssFeed item, boolean empty)
    {
        super.updateItem(item, empty);
        if (!empty && item != null)
        {
            iconView.setImage(item.getIcon());
            MenuItem menuItem = new MenuItem("Remove this feed");
            menuItem.setOnAction(event -> removeFeed(item));
            setContextMenu(new ContextMenu(menuItem));
            System.out.println("not empty cell updated");
            titleLabel.setText(item.getTitle());
        }
        else
        {
            // Necessary if we remove a rss
            setContextMenu(null);
            iconView.setImage(null);
            titleLabel.setText(null);
        }
    }
}
