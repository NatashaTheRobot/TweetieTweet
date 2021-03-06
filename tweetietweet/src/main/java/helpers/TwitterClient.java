package helpers;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/**
 * Created by Natasha Murashev on 2/6/14.
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "5Sf1cLdNDCiPlrc6ZEHNA";       // Change this
    public static final String REST_CONSUMER_SECRET = "UnThC6gI0am9IwHRrqz3RB4L8M0EpVQqGZ9HvKAUrkk"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://natashatherobotTweetieTweet";

    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void getHomeTimeline(AsyncHttpResponseHandler handler, String sinceId, String maxId) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        RequestParams params = null;

        if (sinceId != null) {
            params = new RequestParams();
            params.put("since_id", sinceId);
        }

        if (maxId != null) {
            if (params == null) {
                params = new RequestParams();
            }
            params.put("max_id", maxId);
        }

        client.get(apiUrl, params, handler);
    }

    public void getMentions(AsyncHttpResponseHandler handler, String sinceId, String maxId) {
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        RequestParams params = null;

        if (sinceId != null) {
            params = new RequestParams();
            params.put("since_id", sinceId);
        }

        if (maxId != null) {
            if (params == null) {
                params = new RequestParams();
            }
            params.put("max_id", maxId);
        }

        client.get(apiUrl, params, handler);
    }

    public void getUserTimeline(AsyncHttpResponseHandler handler, String sinceId, String userId, String maxId) {
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        RequestParams params = null;

        if (sinceId != null) {
            params = new RequestParams();
            params.put("since_id", sinceId);
        }

        if (userId != null) {
            if (params == null) {
                params = new RequestParams();
            }
            params.put("user_id", userId);
        }

        if (maxId != null) {
            if (params == null) {
                params = new RequestParams();
            }
            params.put("max_id", maxId);
        }

        client.get(apiUrl, params, handler);
    }

    public void postStatus(AsyncHttpResponseHandler handler, String status) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", status);
        client.post(apiUrl, params, handler);
    }

    public void getMyInfo(AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("account/verify_credentials.json");
        getClient().get(apiUrl, null, handler);
    }
}
