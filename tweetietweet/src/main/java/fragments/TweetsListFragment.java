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
    private ArrayList<Tweet> tweets;
    private TweetsAdapter tweetsAdapter;
    private ListView lvTweets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frament_tweets_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        tweets = new ArrayList<Tweet>();
        lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
        tweetsAdapter = new TweetsAdapter(getActivity(), tweets);
        lvTweets.setAdapter(tweetsAdapter);
    }

    public TweetsAdapter getTweetsAdapter() {
        return tweetsAdapter;
    }

    public ListView getLvTweets() {
        return lvTweets;
    }
}
