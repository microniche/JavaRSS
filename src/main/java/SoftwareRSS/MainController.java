package SoftwareRSS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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

    @FXML
    private ListView rssFeedsView;

    @FXML
    private TextField rssField;

    private VBox layout = null;

    private ObservableList rssList = null;

    static private final String rssUrl = "http://localhost:8080/ServeurJavaRSS/feeds";
    static private final String articleUrl = "http://localhost:8080/ServeurJavaRSS/articles";

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
            rssFeedsView.setCellFactory(new Callback<ListView<RssFeed>, ListCell>() {
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
        Article[] articleArray = new Article[]
                {
                    new Article("titre nul", "description boaf", "link foireux"),
                        new Article("titre moyen", "description nulle", "link moisi"),
                        new Article("titre trop cool", "description amazing", "link qui pue"),
                        new Article("gegzeg", "description amabfdsbzing", "link quidfbsbsdb pue"),
                        new Article("gsdgbdfhdfhfd", "description amfsdbsazing", "link qusbdbfsi pue"),
                        new Article("bfdsbbsdb", "description amazibfdbbsbbfng", "link qudfsbsdbi pue")
                };
        ObservableList testList = FXCollections.observableArrayList(articleArray);
        articlesView.setItems(testList);
    }

    public void updateRssFeeds()
    {
        RssFeed[] testRssFeedsArray = new RssFeed[]
                {
                    new RssFeed("Flux toutes les actualités - 01net", "http://www.01net.com/rss/info/flux-rss/flux-toutes-les-actualites/"),
                        new RssFeed("Le Monde.fr - Actualités et Infos en France et dans le monde", "http://www.lemonde.fr/rss/une.xml"),
                        new RssFeed("Liberation - A la une sur Libération", "http://rss.liberation.fr/rss/latest/")
                };
        rssList = FXCollections.observableArrayList(testRssFeedsArray);
        rssFeedsView.setItems(rssList);
    }

    public void removeOneFeed(RssFeed feed)
    {
        rssList.remove(feed);
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
        Utils.inform(HttpHandler.sendGet(articleUrl));
    }
    public VBox getLayout()
    {
        return (layout);
    }
}
