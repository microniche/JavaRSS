package SoftwareRSS;

import javafx.scene.image.Image;

import javax.imageio.ImageReader;

/**
 * Created by Aurélien on 11/01/2017.
 */
public class RssFeed {
    private String _title;
    private String _link;
    private Image _icon;

    public RssFeed(String title, String link)
    {
        _title = title;
        _link = link;
        _icon = Utils.readIcoFile(Utils.cutUrl(link) + "/favicon.ico");
    }

    public String getTitle()
    {
        return (_title);
    }

    public String getLink()
    {
        return (_link);
    }

    public Image getIcon()
    {
        return (_icon);
    }
}
