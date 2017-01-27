package SoftwareRSS;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Aurélien on 11/01/2017.
 */
public class Utils {

    // http://www.test.fr/truc.php?name=pierre&age=54 => http://www.test.fr
    static public String cutUrl(String fullUrl)
    {
        try
        {
            URL url = new URL(fullUrl);
            URL url2;
            if (url.getPort() != -1)
            {
                url2 = new URL(url.getProtocol(), url.getHost(), url.getPort(), "");
                return (url2.toString());
                //return (url.getProtocol() + "://" + url.getHost() + ":" + url.getPort());
            }
            else
            {
                url2 = new URL(url.getProtocol(), url.getHost(), "");
                return (url2.toString());
                //return (url.getProtocol() + "://" + url.getHost());
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        return (fullUrl);
    }
    static public Image readIcoFile(String path) {
        try {
            System.out.println(path);
            URL url = new URL(path);
            ImageInputStream stream = ImageIO.createImageInputStream(url.openStream());
            ImageReader reader = ImageIO.getImageReaders(stream).next();

            reader.setInput(stream);
            int count = reader.getNumImages(true);

            System.out.println("count: " + count);

            /*for (int i = 0; i < count; i++) {
                BufferedImage bufferedImage = reader.read(i, null);
                Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
                fxImages.add(fxImage);
            }*/

            BufferedImage bufferedImage = reader.read(0, null);
            Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            stream.close(); // Remember to close/dispose in a finally block
            reader.dispose();
            return (fxImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }
    static public void inform(String message)
    {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION, message);
        dialog.setHeaderText("Oops");
        dialog.setTitle("Rss infos");
        dialog.show();
    }
}
