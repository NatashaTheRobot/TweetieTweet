package com.natashatherobot.tweetietweet;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fragments.HomeTimelineFragment;
import fragments.MentionsFragment;

public class TweetsActivity extends ActionBarActivity implements ActionBar.TabListener {

    private HomeTimelineFragment homeTimelineFragment;
    private MentionsFragment mentionsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

        setupNavigationTabs();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tweets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_profile) {
            displayProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayProfile() {
        Intent intent = new Intent(TweetsActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void setupNavigationTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        ActionBar.Tab tabHome = actionBar.newTab().setText("Home")
                                                  .setTag("HomeTimelineFragment")
                                                  .setIcon(R.drawable.ic_action_timeline)
                                                  .setTabListener(this);

        ActionBar.Tab tabMentions = actionBar.newTab().setText("Mentions")
                                                      .setTag("MentionsFragment")
                                                      .setIcon(R.drawable.ic_action_mentions)
                                                      .setTabListener(this);

        actionBar.addTab(tabHome);
        actionBar.addTab(tabMentions);
        actionBar.selectTab(tabHome);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        FragmentManager manager = getSupportFragmentManager();
        fragmentTransaction = manager.beginTransaction();
        if (tab.getTag() == "HomeTimelineFragment") {
            if (homeTimelineFragment == null) {
                homeTimelineFragment = new HomeTimelineFragment();
            }
            fragmentTransaction.replace(R.id.frameContainer, homeTimelineFragment);
        } else {
            if (mentionsFragment == null) {
                mentionsFragment = new MentionsFragment();
            }
            fragmentTransaction.replace(R.id.frameContainer, mentionsFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}