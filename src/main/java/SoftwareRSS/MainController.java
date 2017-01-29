package SoftwareRSS;

import com.JavaRSS.Beans.Article;
import com.JavaRSS.Beans.Feed;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

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

    @FXML
    private ListView rssFeedsView;

    @FXML
    private TextField rssField;

    private VBox layout = null;

    private ObservableList<Feed> feedList = null;

    static private final String rssUrl = "http://localhost:8080/ServeurJavaRSS/feeds";
    static private final String articleUrl = "http://localhost:8080/ServeurJavaRSS/articles";
    static private final String articleReadUrl = "http://localhost:8080/ServeurJavaRSS/articleread";

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
                    return new CustomArticleListCell();
                }
            });
            rssFeedsView.setCellFactory(new Callback<ListView<Feed>, ListCell>() {
                @Override
                public ListCell call(ListView param) {
                    return new CustomRssFeedListCell();
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateArticles()
    {
        List<Article> articles = Utils.deserializeArticlesList(HttpHandler.sendGetInputVersion(articleUrl));
        System.out.println("display: " + articles.size());
        ObservableList testList = FXCollections.observableArrayList(articles);
        articlesView.setItems(testList);
    }

    public void updateRssFeeds()
    {
        //System.out.print(HttpHandler.sendGet(rssUrl));
        List<Feed> feeds = Utils.deserializeFeedsList(HttpHandler.sendGetInputVersion(rssUrl));
        System.out.println("display: " + feeds.size());
        for (Feed feed : feeds)
            System.out.println(feed.getTitle());
        feedList = FXCollections.observableArrayList(feeds);
        rssFeedsView.setItems(feedList);
    }

    public void removeOneFeed(Feed feed)
    {
        feedList.remove(feed);
    }

    @FXML
    private void loginOrLogout()
    {
        if (HttpHandler.cookiesToProvide == null)
            LoginController.showLoginWindow();
        else
        {
            LoginController.sendLogoutRequest();
            HttpHandler.cookiesToProvide = null;
            updateLogTexts("You are not logged in", "Login");
        }
    }

    public void updateLogTexts(String labelText, String buttonText)
    {
        logLabel.setText(labelText);
        logButton.setText(buttonText);
    }

    public ObservableList<Feed> getFeedList()
    {
        return (feedList);
    }

    @FXML
    private void addRss()
    {
        if (HttpHandler.cookiesToProvide == null)
            System.out.println("You are not logged");
        else
        HttpHandler.sendPost(rssUrl, "url", rssField.getText());
        System.out.println(rssField.getText());
    }

    @FXML
    private void getArticles()
    {
        String ret = HttpHandler.sendGet(articleUrl);
        Utils.inform(ret);
        System.out.println(ret);
    }
    public VBox getLayout()
    {
        return (layout);
    }
}
