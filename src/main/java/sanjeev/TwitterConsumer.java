package sanjeev;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by sanjeev on 7/1/14.
 */
public class TwitterConsumer {
    public JSONArray fetch(HashMap<String, String> parsedJson) {
        JSONArray outputJSON = new JSONArray();
        String url = "https://api.twitter.com/1.1/search/tweets.json";
        StringBuilder queryBuilder = new StringBuilder("?");
        queryBuilder.append("q=" + parsedJson.get("query"));
        queryBuilder.append("&geocode=" + parsedJson.get("lat") + "," + parsedJson.get("long") + "," + parsedJson.get("rad"));
        queryBuilder.append("&result_type=popular");
        queryBuilder.append("&rpp=" + parsedJson.get("total"));
        queryBuilder.append(Utility.getAuthorizationToken());
        String urlQuery = "";
        try {
            System.out.println(queryBuilder.toString());
            urlQuery = URLEncoder.encode(queryBuilder.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(url + urlQuery);
        Client client = Client.create();
        WebResource webResource = client.resource(url + urlQuery);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String output = String.valueOf(response.getEntity(JSONObject.class));


        try {
            JSONObject jsonObject = new JSONObject(output);
            JSONArray jsonMainArr = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonMainArr.length(); i++) {
                JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
                String text = childJSONObject.getString("text");
                outputJSON.put(text);
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return outputJSON;

    }
}
