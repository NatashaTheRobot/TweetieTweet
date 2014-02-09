package fragments;

import android.os.Bundle;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import helpers.EndlessScrollListener;
import helpers.TweetieBirdApp;

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
                addTweetsFromJsonArray(jsonTweets);

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
        TweetieBirdApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                addTweetsFromJsonArray(jsonTweets);
            }
        }, getTweetUid(totalItemsCount - 1));
    }
}
