package helpers;

import android.content.Context;

import com.activeandroid.app.Application;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import models.User;

/**
 * Created by Natasha Murashev on 2/6/14.
 */
public class TweetieBirdApp extends Application {
    private static Context context;
    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        TweetieBirdApp.context = this;

        // Create global configuration and initialize ImageLoader with this configuration
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public static TwitterClient getRestClient() {
        return (TwitterClient) TwitterClient.getInstance(TwitterClient.class, TweetieBirdApp.context);
    }

    public static void setUser(User user) {
        TweetieBirdApp.user = user;
    }

    public static User getUser() {
        return TweetieBirdApp.user;
    }

}
