package models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Natasha Murashev on 2/6/14.
 */

@Table(name="tweets")
public class Tweet extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="body")
    private String body;

    @Column(name="uid", unique=true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String uid;

    @Column(name="favorited")
    private boolean favorited;

    @Column(name="retweeted")
    private boolean retweeted;

    @Column(name="user")
    private User user;

    @Column(name="createdAt")
    private Date createdAt;

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public String getUId() {
        return uid;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static Tweet fromJson(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = String.valueOf(jsonObject.getLong("id"));
            tweet.favorited = jsonObject.getBoolean("favorited");
            tweet.retweeted = jsonObject.getBoolean("retweeted");
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

            String createdAtDate = jsonObject.getString("created_at"); //"Tue Aug 28 21:16:23 +0000 2012"
            try {
                tweet.createdAt = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.getDefault()).parse(createdAtDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tweet.save();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject tweetJson = null;
            try {
                tweetJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Tweet tweet = Tweet.fromJson(tweetJson);
            if (tweet != null) {
                tweets.add(tweet);
            }
        }

        return tweets;
    }
}
