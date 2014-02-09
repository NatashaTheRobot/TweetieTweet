package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.natashatherobot.tweetietweet.R;

import org.json.JSONArray;

import java.util.ArrayList;

import adapters.TweetsAdapter;
import models.Tweet;

/**
 * Created by Natasha Murashev on 2/8/14.
 */
public class TweetsListFragment extends Fragment {
    private ArrayList<Tweet> tweets;
    private TweetsAdapter tweetsAdapter;
    private ListView lvTweets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tweets_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        tweets = new ArrayList<Tweet>();
        lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
        tweetsAdapter = new TweetsAdapter(getActivity(), tweets);
        lvTweets.setAdapter(tweetsAdapter);
    }

    public ListView getLvTweets() {
        return lvTweets;
    }

    public void addTweetsFromJsonArray(JSONArray jsonTweets) {
        ArrayList<Tweet> newTweets = Tweet.fromJson(jsonTweets);
        tweetsAdapter.addAll(newTweets);
    }

    public void addTweet(Tweet tweet, int position) {
       tweetsAdapter.insert(tweet, position);
    }

    public String getTweetUid(int position) {
        Tweet tweet = tweetsAdapter.getItem(position);
        return tweet.getUId();
    }

}
