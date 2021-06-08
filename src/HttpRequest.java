import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpRequest
{
    private static HttpURLConnection connection;
    HttpRequest()
    {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        //Method 1: java.net.http
        try {
            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=1c20a8ea81d5429cbf2fdc8fa15816a7");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            //System.out.println(status);
            if(status > 299)
            {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null)
                {
                    responseContent.append(line);
                }
                reader.close();
            }
            else
            {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null)
                {
                    responseContent.append(line);
                }
            }
            System.out.println(responseContent.toString());
            parse(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


    }
    public static String parse(String responseBody)
    {
        System.out.println(responseBody);
        Gson g = new Gson();
        ExchangeRateFromApi rateFromApi = g.fromJson(responseBody,ExchangeRateFromApi.class);
        System.out.println(rateFromApi.base);
        /*JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i<albums.length();i++)
        {
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt( "id");
            String title = album.getString("title");
            int userId = album.getInt("userId");
            System.out.println(id + " " + title + "  " + userId);
        }
        */
        return null;
    }
}
