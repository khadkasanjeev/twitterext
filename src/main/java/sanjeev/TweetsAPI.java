package sanjeev;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;

/**
 * Created by sanjeev on 7/1/14.
 */

// Pre - requisite: Authorization Token OAuth to be acquired. Please supply correct token at Utility class manually.

@Path("/tweets")
public class TweetsAPI {

    @Path("/top/{total}")
    @GET
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweets(@PathParam("total") String number, @Context UriInfo info) throws Exception {
        JSONObject result = null;
        JSONArray outputArray = null;
        try {
            HashMap<String, String> parsedJSON = Utility.parseRequestToHashMap(info);
            if (parsedJSON.keySet().contains("query") &&  parsedJSON.keySet().contains("lat") && parsedJSON.keySet().contains("long") && parsedJSON.keySet().contains("rad")){
                TwitterConsumer twitterConsumer = new TwitterConsumer();
                parsedJSON.put("total", number);
                outputArray= twitterConsumer.fetch(parsedJSON);
            }
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok(outputArray).build();
    }


}

