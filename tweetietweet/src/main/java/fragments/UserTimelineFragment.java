package fragments;

import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import helpers.EndlessScrollListener;
import helpers.TweetieBirdApp;
import models.Tweet;
import models.User;

/**
 * Created by Natasha Murashev on 2/8/14.
 */
public class UserTimelineFragment extends TweetsListFragment {
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setUser(User user) {
        this.user = user;
        getUserTimelineData();
    }

    private void getUserTimelineData() {
        TweetieBirdApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
                getTweetsAdapter().addAll(tweets);

                getLvTweets().setOnScrollListener(new EndlessScrollListener() {

                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        customLoadMoreDataFromApi(totalItemsCount);
                    }
                });
            }
        }, null, user.getUId());
    }

    private void customLoadMoreDataFromApi(int totalItemsCount) {
        Tweet lastTweet = getTweetsAdapter().getItem(totalItemsCount - 1);
        TweetieBirdApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                ArrayList<Tweet> newTweets = Tweet.fromJson(jsonTweets);
                getTweetsAdapter().addAll(newTweets);
            }
        }, lastTweet.getUId(), user.getUId());
    }

}
