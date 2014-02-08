package com.natashatherobot.tweetietweet;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

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
    }


}