package SoftwareRSS;

import com.JavaRSS.Beans.Feed;
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
public class CustomRssFeedListCell extends ListCell<Feed> {
    @FXML
    private Label titleLabel;

    @FXML
    private ImageView iconView;

    static private final String rssUrl = "http://localhost:8080/ServeurJavaRSS/feeds";

    private Feed feed = null;

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

    @FXML
    private void removeFeed(Feed feed)
    {
        //inform API to remove the feed
        if (feed != null) {
            HttpHandler.sendDelete(rssUrl, "feed_id", String.valueOf(feed.getId()));
            Main.mainController.removeOneFeed(feed);
        }
    }

    @Override
    public void updateItem(Feed item, boolean empty)
    {
        super.updateItem(item, empty);
        if (!empty && item != null)
        {
            iconView.setImage(Utils.readIcoFile(Utils.cutUrl(item.getLink()) + "/favicon.ico"));
            MenuItem menuItem = new MenuItem("Remove this feed");
            menuItem.setOnAction(event -> removeFeed(item));
            setContextMenu(new ContextMenu(menuItem));
            System.out.println("not empty cell updated");
            titleLabel.setText(item.getTitle());
            feed = item;
        }
        else
        {
            // Necessary if we remove a rss
            setContextMenu(null);
            iconView.setImage(null);
            titleLabel.setText(null);
            feed = null;
        }
    }
}
