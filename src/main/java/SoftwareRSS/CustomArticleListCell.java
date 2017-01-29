package SoftwareRSS;

import com.JavaRSS.Beans.Article;
import com.JavaRSS.Beans.Feed;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Created by Aurélien on 10/01/2017.
 */
public class CustomArticleListCell extends ListCell<Article> {
    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label rssLabel;

    @FXML
    private ImageView readView;

    private Article article = null;

    static private final String articleReadUrl = "http://localhost:8080/ServeurJavaRSS/articleread";

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

    private String getFeedTitleFromArticle(Article article)
    {
        for (Feed feed : Main.mainController.getFeedList())
        {
            if (feed.getId() == article.getOwnerRss())
                return (feed.getTitle());
        }
        return (null);
    }

    @Override
    public void updateItem(Article item, boolean empty)
    {
        super.updateItem(item, empty);
        if (!empty && item != null)
        {
            article = item;
            if (!item.getIsRead())
                readView.setVisible(true);
            dateLabel.setText(item.getPubDate().toString());
            rssLabel.setText(getFeedTitleFromArticle(item));
            descriptionLabel.setText(item.getTitle());
        }
        else
        {
            readView.setVisible(false);
            dateLabel.setText(null);
            rssLabel.setText(null);
            descriptionLabel.setText(null);
            article = null;
        }
    }

    @FXML
    private void goToArticle()
    {
        if (article != null)
        {
            HttpHandler.sendGet(articleReadUrl, "id", String.valueOf(article.getId()));
            readView.setVisible(false);
            article.setIsRead(true);
            Main.hostServices.showDocument(article.getUrl());
        }
    }
}
