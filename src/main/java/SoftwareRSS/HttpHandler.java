package SoftwareRSS;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Aurélien on 10/01/2017.
 */
public class HttpHandler {
    static public String sendPost(String url, String... params)
    {
        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

            String urlParameters = params[0] + '=' + params[1]; // fail if there is no params but there should be
            for (int i = 2; i < params.length; i = i + 2)
            {
                urlParameters += '&' + params[i] + '=' + params[i + 1];
            }

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return (response.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
}
