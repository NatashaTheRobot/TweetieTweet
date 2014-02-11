package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.natashatherobot.tweetietweet.R;

import org.json.JSONArray;

import java.util.ArrayList;

import adapters.TweetsAdapter;
import helpers.Constants;
import helpers.EndlessScrollListener;
import helpers.TweetieBirdApp;
import helpers.TwitterClient;
import models.Tweet;

/**
 * Created by Natasha Murashev on 2/8/14.
 */
public abstract class TweetsListFragment extends Fragment {
    private ArrayList<Tweet> tweets;
    private TweetsAdapter tweetsAdapter;
    private ListView lvTweets;
    public Constants.FragmentType fragmentType;
    private TwitterClient twitterClient = TweetieBirdApp.getRestClient();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragmentType();
        tweets = new ArrayList<Tweet>();
        tweetsAdapter = new TweetsAdapter(getActivity(), tweets);
        loadTweets();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets_list, container, false);

        lvTweets = (ListView) view.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(tweetsAdapter);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(totalItemsCount);
            }
        });

        return view;
    }

    public abstract void setFragmentType();

    public ListView getLvTweets() {
        return lvTweets;
    }

    public void addTweetsFromJsonArray(JSONArray jsonTweets, int positonOfItemToRemove) {
        ArrayList<Tweet> newTweets = Tweet.fromJson(jsonTweets);
        if (positonOfItemToRemove != -1) {
            newTweets.remove(positonOfItemToRemove);
        }
        tweetsAdapter.addAll(newTweets);
    }

    public void addTweet(Tweet tweet, int position) {
       tweetsAdapter.insert(tweet, position);
    }

    public String getTweetUid(int position) {
        Tweet tweet = tweetsAdapter.getItem(position);
        return tweet.getUId();
    }

    private void loadTweets() {
        switch (fragmentType) {
            case HOME:
                twitterClient.getHomeTimeline(new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONArray jsonTweets) {
                        addTweetsFromJsonArray(jsonTweets, -1);
                    }
                }, null, null);
            case MENTIONS:
                twitterClient.getMentions(new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(JSONArray jsonTweets) {
                        addTweetsFromJsonArray(jsonTweets, -1);
                    }
                }, null, null);
        }
    }

    private void customLoadMoreDataFromApi(int totalItemsCount) {
        String maxId = getTweetUid(totalItemsCount - 1);
        switch (fragmentType) {
            case HOME:
                twitterClient.getHomeTimeline(new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONArray jsonTweets) {
                        addTweetsFromJsonArray(jsonTweets, 0);
                    }
                }, null, maxId);
                break;
            case MENTIONS:
                twitterClient.getMentions(new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(JSONArray jsonTweets) {
                        addTweetsFromJsonArray(jsonTweets, 0);
                    }
                }, null, maxId);
                break;
        }
    }

}
