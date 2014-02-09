package adapters;

/**
 * Created by Natasha Murashev on 2/8/14.
 */

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.natashatherobot.tweetietweet.ProfileActivity;
import com.natashatherobot.tweetietweet.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import models.Tweet;
import models.User;

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
        final User user = tweet.getUser();

        ImageView imageView = (ImageView) view.findViewById(R.id.ivProfile);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        imageLoader.displayImage(user.getProfileImageUrl(), imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("user", user);
                getContext().startActivity(intent);
            }
        });

        TextView nameView = (TextView) view.findViewById(R.id.tvName);
        String formattedName = "<b>" + user.getName() + " <b>" + "<small><font color='#777777'>@" + user.getScreenName() + "</font></small>";
        nameView.setText(Html.fromHtml(formattedName));

        TextView tvTimeStamp = (TextView) view.findViewById(R.id.tvTimeStamp);
        String timeString = DateUtils.getRelativeDateTimeString(getContext(),
                tweet.getCreatedAt().getTime(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.WEEK_IN_MILLIS,
                0).toString();
        String timeStringModified = timeString.split(",")[0];
        String formattedTime = "<small><font color='#777777'>" + timeStringModified + "</font></small>";
        tvTimeStamp.setText(Html.fromHtml(formattedTime));

        TextView bodyView = (TextView) view.findViewById(R.id.tvBody);
        bodyView.setText(Html.fromHtml(tweet.getBody()));

        return view;
    }
}
