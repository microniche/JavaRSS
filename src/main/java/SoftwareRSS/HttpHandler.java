package SoftwareRSS;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Aurélien on 10/01/2017.
 */
public class HttpHandler {
    static public String cookiesToProvide = null;
    static public String previousSetCookie = null;

    static private void setCookies(HttpURLConnection con)
    {
        if (cookiesToProvide != null)
            for (String cookie : cookiesToProvide.split(";"))
            {
                con.addRequestProperty("Cookie", cookie.trim());
                System.out.println(cookie);
            }
    }

    static public String sendPost(String url, String... params)
    {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            setCookies(con);
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
            if (con.getHeaderField("Set-Cookie") == null)
            {
                System.out.println("No cookie");
                previousSetCookie = null;
            }
            else
            {
                System.out.println("Cookie = " + con.getHeaderField("Set-Cookie"));
                previousSetCookie = con.getHeaderField("Set-Cookie");
            }
            return (response.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (null);
    }
    static public String sendGet(String url)
    {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            setCookies(con);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            if (con.getHeaderField("Set-Cookie") == null)
            {
                System.out.println("No cookie");
                previousSetCookie = null;
            }
            else
            {
                System.out.println("Cookie = " + con.getHeaderField("Set-Cookie"));
                previousSetCookie = con.getHeaderField("Set-Cookie");
            }
            return (response.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    static public InputStream sendGetInputVersion(String url)
    {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            setCookies(con);

            int responseCode = con.getResponseCode();

            if (con.getHeaderField("Set-Cookie") == null)
            {
                System.out.println("No cookie");
                previousSetCookie = null;
            }
            else
            {
                System.out.println("Cookie = " + con.getHeaderField("Set-Cookie"));
                previousSetCookie = con.getHeaderField("Set-Cookie");
            }
            return (con.getInputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    static public String sendGet(String url, String... params)
    {
        String urlParameters = params[0] + '=' + params[1]; // fail if there is no params but there should be
        for (int i = 2; i < params.length; i = i + 2)
        {
            urlParameters += '&' + params[i] + '=' + params[i + 1];
        }
        return (sendGet(url + '?' + urlParameters));
    }

    static public String sendDelete(String url)
    {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("DELETE");

            setCookies(con);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            if (con.getHeaderField("Set-Cookie") == null)
            {
                System.out.println("No cookie");
                previousSetCookie = null;
            }
            else
            {
                System.out.println("Cookie = " + con.getHeaderField("Set-Cookie"));
                previousSetCookie = con.getHeaderField("Set-Cookie");
            }
            return (response.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    static public String sendDelete(String url, String... params)
    {
        String urlParameters = params[0] + '=' + params[1]; // fail if there is no params but there should be
        for (int i = 2; i < params.length; i = i + 2)
        {
            urlParameters += '&' + params[i] + '=' + params[i + 1];
        }
        return (sendDelete(url + '?' + urlParameters));
    }
}
