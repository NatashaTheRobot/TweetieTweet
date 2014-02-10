package fragments;

import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import helpers.EndlessScrollListener;
import helpers.TweetieBirdApp;

/**
 * Created by Natasha Murashev on 2/8/14.
 */
public class MentionsFragment extends TweetsListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TweetieBirdApp.getRestClient().getMentions(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                addTweetsFromJsonArray(jsonTweets, -1);

                getLvTweets().setOnScrollListener(new EndlessScrollListener() {

                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        customLoadMoreDataFromApi(totalItemsCount);
                    }
                });
            }
        }, null, null);
    }

    private void customLoadMoreDataFromApi(int totalItemsCount) {
        TweetieBirdApp.getRestClient().getMentions(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                addTweetsFromJsonArray(jsonTweets, 0);
            }
        }, null, getTweetUid(totalItemsCount - 1));
    }

}
