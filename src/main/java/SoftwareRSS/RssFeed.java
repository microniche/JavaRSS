package SoftwareRSS;

/**
 * Created by Aurélien on 11/01/2017.
 */
public class RssFeed {
    private String _title;
    private String _link;

    public RssFeed(String title, String link)
    {
        _title = title;
        _link = link;
    }

    public String getTitle()
    {
        return (_title);
    }

    public String getLink()
    {
        return (_link);
    }
}
