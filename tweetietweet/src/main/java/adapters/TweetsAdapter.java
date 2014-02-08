package adapters;

/**
 * Created by Natasha Murashev on 2/8/14.
 */

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import models.Tweet;

public class TweetsAdapter extends ArrayAdapter<Tweet> {

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(com.natashatherobot.tweetietweet.R.layout.tweet_item, null);
        }

        Tweet tweet = getItem(position);

        ImageView imageView = (ImageView) view.findViewById(com.natashatherobot.tweetietweet.R.id.ivProfile);
        ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), imageView);

        TextView nameView = (TextView) view.findViewById(com.natashatherobot.tweetietweet.R.id.tvName);
        String formattedName = "<b>" + tweet.getUser().getName() + " <b>" + "<small><font color='#777777'>@" + tweet.getUser().getScreenName() + "</font></small>";
        nameView.setText(Html.fromHtml(formattedName));

        TextView tvTimeStamp = (TextView) view.findViewById(com.natashatherobot.tweetietweet.R.id.tvTimeStamp);
        String timeString = DateUtils.getRelativeDateTimeString(getContext(),
                tweet.getCreatedAt().getTime(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.WEEK_IN_MILLIS,
                0).toString();
        String formattedTime = "<small><font color='#777777'>" + timeString + "</font></small>";
        tvTimeStamp.setText(Html.fromHtml(formattedTime));

        TextView bodyView = (TextView) view.findViewById(com.natashatherobot.tweetietweet.R.id.tvBody);
        bodyView.setText(Html.fromHtml(tweet.getBody()));

        return view;
    }
}
