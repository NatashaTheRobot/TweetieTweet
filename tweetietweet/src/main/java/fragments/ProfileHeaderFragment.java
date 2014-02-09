package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.natashatherobot.tweetietweet.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import models.User;

/**
 * Created by Natasha Murashev on 2/9/14.
 */
public class ProfileHeaderFragment extends Fragment {
    private User user;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_header, container, false);
        return view;
    }

    public void setUser(User user) {
        this.user = user;
        configureUserInfo();
    }

    private void configureUserInfo() {
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(user.getName());

        TextView tvTagline = (TextView) view.findViewById(R.id.tvTagline);
        tvTagline.setText(user.getTagline());

        TextView tvFollowers = (TextView) view.findViewById(R.id.tvFollowers);
        tvFollowers.setText(user.getFollowersCount() + " followers");

        TextView tvFollowing = (TextView) view.findViewById(R.id.tvFollowing);
        tvFollowing.setText(user.getFriendsCount() + " following");

        ImageView ivProfileImage = (ImageView) view.findViewById(R.id.ivProfileImage);
        ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
    }
}
