package models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Natasha Murashev on 2/6/14.
 */
@Table(name="users")
public class User extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="name")
    private String name;

    @Column(name="uid", unique=true, onUniqueConflict = Column.ConflictAction.REPLACE)
    private String uid;

    @Column(name="screenName")
    private String screenName;

    @Column(name="profileBgImageUrl")
    private String profileBgImageUrl;

    @Column(name="profileImageUrl")
    private String profileImageUrl;

    @Column(name="numTweets")
    private int numTweets;

    @Column(name="followersCount")
    private int followersCount;

    @Column(name="friendsCount")
    private int friendsCount;

    @Column(name="tagline")
    private String tagline;

    public String getName() {
        return name;
    }

    public String getUId() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileBackgroundImageUrl() {
        return profileBgImageUrl;
    }

    public int getNumTweets() {
        return numTweets;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getTagline() {
        return tagline;
    }

    public static User fromJson(JSONObject json) {
        User u = new User();
        try {
            u.name = json.getString("name");
            u.uid = json.getString("id_str");
            u.screenName = json.getString("screen_name");
            u.profileBgImageUrl = json.getString("profile_background_image_url");
            u.numTweets = json.getInt("statuses_count");
            u.followersCount = json.getInt("followers_count");
            u.friendsCount = json.getInt("friends_count");
            u.profileImageUrl = json.getString("profile_image_url_https");
            u.tagline= json.getString("description");
            u.save();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }
}
