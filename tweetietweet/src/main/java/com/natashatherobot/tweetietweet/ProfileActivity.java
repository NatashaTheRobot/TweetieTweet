package com.natashatherobot.tweetietweet;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import fragments.ProfileHeaderFragment;
import fragments.UserTimelineFragment;
import models.User;

public class ProfileActivity extends ActionBarActivity {
    ProfileHeaderFragment profileHeaderFragment;
    UserTimelineFragment userTimelineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FragmentManager manager = getSupportFragmentManager();
        profileHeaderFragment = (ProfileHeaderFragment) manager.findFragmentById(R.id.fragmentProfileHeader);
        userTimelineFragment = (UserTimelineFragment) manager.findFragmentById(R.id.fragmentUserTimeline);

        loadUserInfo();
    }

    private void loadUserInfo() {
        User user = (User) getIntent().getSerializableExtra("user");
        getActionBar().setTitle("@" + user.getScreenName());
        profileHeaderFragment.setUser(user);
        userTimelineFragment.setUser(user);
    }


}
