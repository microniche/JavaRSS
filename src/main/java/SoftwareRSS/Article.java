package SoftwareRSS;

/**
 * Created by Aurélien on 10/01/2017.
 */
public class Article {
    private String _title;
    private String _description;
    private String _link;

    public Article()
    {
        _title = null;
        _description = null;
        _link = null;
    }

    public Article(String title, String description, String link)
    {
        _title = title;
        _description = description;
        _link = link;
    }

    public String getTitle()
    {
        return (_title);
    }

    public String getDescription()
    {
        return (_description);
    }

    public String getLink()
    {
        return (_link);
    }
}
