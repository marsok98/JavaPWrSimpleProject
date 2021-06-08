import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;

public class HttpRequestModern
{
    private static HttpURLConnection connection;

    HttpRequestModern()
    {
        //method 2: using java.net.http.httpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://openexchangerates.org/api/latest.json?app_id=1c20a8ea81d5429cbf2fdc8fa15816a7")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(HttpRequestModern::parse)
                .join();
    }
    public static String parse(String responseBody)
    {
        System.out.println(responseBody);
        Gson g = new Gson();

        ExchangeRateFromApi rateFromApi = g.fromJson(responseBody,ExchangeRateFromApi.class);
        System.out.println(rateFromApi.base);
        System.out.println(rateFromApi.rates.get("AED"));//dostep do konkretnego obiektu w mapie javovej
        /*JSONArray rates = new JSONArray(responseBody);
        for (int i = 0; i<rates.length();i++)
        {
            JSONObject jsonRates = rates.getJSONObject(i);
            int currency = jsonRates.getInt( "base");

            Dictionary<String, BigDecimal> userId = jsonRates.get"rates");
            System.out.println(id + " " + title + "  " + userId);



        }
        */

        return null;
    }
}
