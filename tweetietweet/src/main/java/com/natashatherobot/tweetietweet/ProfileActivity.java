package com.natashatherobot.tweetietweet;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import helpers.TweetieBirdApp;
import models.User;

public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        configureUserInfo();
    }

    private void configureUserInfo() {
        if (TweetieBirdApp.getUser() == null) {
            TweetieBirdApp.getRestClient().getMyInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(JSONObject jsonUser) {
                    try {
                        User user = User.fromJson(jsonUser);
                        TweetieBirdApp.setUser(user);

                        loadUserInfo(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable arg0, String message) {
                    Toast.makeText(getBaseContext(), "Oops, something went wrong! Please try again", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            loadUserInfo(TweetieBirdApp.getUser());
        }


    }

    private void loadUserInfo(User user) {
        getActionBar().setTitle("@" + user.getScreenName());

        TextView tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(user.getName());

        TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
        tvTagline.setText(user.getTagline());

        TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        tvFollowers.setText(user.getFollowersCount() + " followers");

        TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        tvFollowing.setText(user.getFriendsCount() + " following");

        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);

    }


}
