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
import helpers.EndlessScrollListener;
import helpers.TweetieBirdApp;
import models.Tweet;

/**
 * Created by Natasha Murashev on 2/8/14.
 */
public class TweetsListFragment extends Fragment {
    ArrayList<Tweet> tweets;
    TweetsAdapter tweetsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frament_tweets_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        tweets = new ArrayList<Tweet>();
        ListView lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
        tweetsAdapter = new TweetsAdapter(getActivity(), tweets);
        lvTweets.setAdapter(tweetsAdapter);
    }

    public TweetsAdapter getTweetsAdapter() {
        return tweetsAdapter;
    }

    public void customLoadMoreDataFromApi(int totalItemsCount) {
        Tweet lastTweet = tweets.get(totalItemsCount - 1);
        String sinceId = String.valueOf(lastTweet.getUId());
        TweetieBirdApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray jsonTweets) {
                ArrayList<Tweet> newTweets = Tweet.fromJson(jsonTweets);
                tweets.addAll(newTweets);
                tweetsAdapter.notifyDataSetChanged();
            }
        }, sinceId);
    }

//    private void configureTweets() {
//        TweetieBirdApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(JSONArray jsonTweets) {
//                tweets = Tweet.fromJson(jsonTweets);
//                ListView lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
//                tweetsAdapter = new TweetsAdapter(getActivity(), tweets);
//                lvTweets.setAdapter(tweetsAdapter);
//
//                lvTweets.setOnScrollListener(new EndlessScrollListener(){
//
//                    @Override
//                    public void onLoadMore(int page, int totalItemsCount) {
//                        customLoadMoreDataFromApi(totalItemsCount);
//                    }
//
//                });
//
//            }
//        }, null);
//    }
}
