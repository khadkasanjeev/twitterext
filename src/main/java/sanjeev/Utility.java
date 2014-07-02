package sanjeev;

import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sanjeev on 7/1/14.
 */
public class Utility {
    public static final String OAUTH_CONSUMER_KEY = "xxxxx";
    public static final String OAUTH_NONCE = "xxxxx";
    public static final String OAUTH_SIGNATURE_METHOD = "HMAC-SHA1";
    public static final long OAUTH_TIMESTAMP = 12234546;
    public static final String OAUTH_TOKEN = "xxxxx";
    public static final String OAUTH_VERSION = "1.1";
    public static final String OAUTH_SIGNATURE = "xxxxx";



    public static HashMap<String, String> parseRequestToHashMap(UriInfo info) {
        HashMap<String, String> parsedJson = new HashMap<String, String>();
        try {
            for (Map.Entry<String, List<String>> e : info.getQueryParameters().entrySet()) {
                parsedJson.put(e.getKey(), e.getValue().get(0) == null ? "" : e.getValue().get(0));
            }

            return parsedJson;
        } catch (Exception e) {
            return parsedJson;
        }

    }

    // Pre- Condition: Get Authorization Token
    public static String getAuthorizationToken()
    {
        StringBuilder authorizationToken = new StringBuilder("&");
        authorizationToken.append("oauth_consumer_key="+OAUTH_CONSUMER_KEY);
        authorizationToken.append("&oauth_nonce="+OAUTH_NONCE);
        authorizationToken.append("&oauth_signature_method="+OAUTH_SIGNATURE_METHOD);
        authorizationToken.append("&oauth_timestamp="+OAUTH_TIMESTAMP);
        authorizationToken.append("&oauth_token="+OAUTH_TOKEN);
        authorizationToken.append("&oauth_version="+OAUTH_VERSION);
        authorizationToken.append("&oauth_signature="+OAUTH_SIGNATURE);
        return authorizationToken.toString();
    }
}
