package fragments;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import helpers.EndlessScrollListener;
import helpers.TweetieBirdApp;
import models.Tweet;

/**
 * Created by Natasha Murashev on 2/8/14.
 */
public class HomeTimelineFragment extends TweetsListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TweetieBirdApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
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
        }, null);
    }

    private void customLoadMoreDataFromApi(int totalItemsCount) {
        Tweet lastTweet = getTweetsAdapter().getItem(totalItemsCount - 1);
        String sinceId = String.valueOf(lastTweet.getUId());
        TweetieBirdApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                ArrayList<Tweet> newTweets = Tweet.fromJson(jsonTweets);
                getTweetsAdapter().addAll(newTweets);
            }
        }, sinceId);
    }
}
