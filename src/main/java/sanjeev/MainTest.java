package sanjeev;

import org.codehaus.jettison.json.JSONArray;

import java.util.HashMap;

/**
 * Created by sanjeev on 7/1/14.
 */
public class MainTest {
    public static void main (String [] args)
    {
        JSONArray outputArray = null;
        HashMap<String, String> parsedJSON = new HashMap<String, String>();
        parsedJSON.put("query","Tom Cruise");
        parsedJSON.put("lat","-122322");
        parsedJSON.put("long","235423");
        parsedJSON.put("rad","12mi");
        if (parsedJSON.keySet().contains("query") &&  parsedJSON.keySet().contains("lat") && parsedJSON.keySet().contains("long") && parsedJSON.keySet().contains("rad")) {
            TwitterConsumer twitterConsumer = new TwitterConsumer();
            parsedJSON.put("total", "10");
            outputArray = twitterConsumer.fetch(parsedJSON);
            System.out.println(outputArray);

        }

    }
}
