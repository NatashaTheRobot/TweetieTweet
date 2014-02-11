package fragments;

import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import helpers.Constants;
import helpers.EndlessScrollListener;
import helpers.TweetieBirdApp;

/**
 * Created by Natasha Murashev on 2/8/14.
 */
public class MentionsFragment extends TweetsListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setFragmentType() {
        fragmentType = Constants.FragmentType.MENTIONS;
    }
}
