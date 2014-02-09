package com.natashatherobot.tweetietweet;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import helpers.TweetieBirdApp;
import models.Tweet;
import models.User;

public class ComposeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        User currentUser = TweetieBirdApp.getUser();

        TextView tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText("@" + currentUser.getName());

        ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);
        ImageLoader.getInstance().displayImage(currentUser.getProfileImageUrl(), ivProfile);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miTweet:
                sendTweet();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_slide_out_bottom);
    }

    private void sendTweet() {
        EditText etStatus = (EditText) findViewById(R.id.etStatus);
        TweetieBirdApp.getRestClient().postStatus(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONObject jsonTweet) {
                Tweet tweet = Tweet.fromJson(jsonTweet);
                Intent data = new Intent();
                data.putExtra("tweet", tweet);
                setResult(RESULT_OK, data);
                finish();
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_slide_out_bottom);
            }

            @Override
            public void onFailure(Throwable arg0, String message) {
                setProgressBarIndeterminateVisibility(false);
                Toast.makeText(ComposeActivity.this, "Tweet not saved", Toast.LENGTH_LONG).show();
            }
        }, etStatus.getText().toString());
    }
}
