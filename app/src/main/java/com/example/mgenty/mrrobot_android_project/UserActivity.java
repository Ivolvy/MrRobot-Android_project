package com.example.mgenty.mrrobot_android_project;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mgenty.mrrobot_android_project.user.LoginFragment;
import com.example.mgenty.mrrobot_android_project.user.UserFragment;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity implements UserFragment.UserListener{
    private static final String TAG = "UserActivity";
    private Firebase mFirebaseRef;
    private UserFragment userFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://androidmrrobot.firebaseio.com/");

        setContentView(R.layout.activity_user);


        userFragment = new UserFragment();

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.userContainer, userFragment)
                    .commit();
        }

        Intent intent = getIntent();
        String userId = intent.getStringExtra(HomeActivity.EXTRA_USER_ID);

        loadUserInformations(userId);

    }


    public void loadUserInformations(String userId){
        mFirebaseRef.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child("name").getValue().toString();
                String userEmail = dataSnapshot.child("email").getValue().toString();

                //call method in UserFragment
                userFragment.updateUserInformation(userName, userEmail);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
