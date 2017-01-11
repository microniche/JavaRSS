package SoftwareRSS;

/**
 * Created by Aurélien on 11/01/2017.
 */
public class RssStream {
    private String _title;
    private String _link;
    private String _imageUrl;

    public RssStream(String title, String link, String imageUrl)
    {
        _title = title;
        _link = link;
        _imageUrl = imageUrl;
    }

    public String getTitle()
    {
        return (_title);
    }

    public String getLink()
    {
        return (_link);
    }

    public String getImageUrl()
    {
        return (_imageUrl);
    }
}
