package com.example.mgenty.mrrobot_android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mgenty.mrrobot_android_project.user.User;
import com.example.mgenty.mrrobot_android_project.user.UserFragment;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //load UserFragment
        userFragment = new UserFragment();

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.userContainer, userFragment)
                    .commit();
        }

        Intent intent = getIntent();
        String userId = intent.getStringExtra(HomeActivity.EXTRA_USER_ID);

        //load user information in order to fill the interface
        loadUserInformations(userId);
    }


    public void loadUserInformations(final String userId){
        mFirebaseRef.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //retrieve the values in firebase to User class
                User user = dataSnapshot.getValue(User.class);
                HomeActivity.setUser(user);
                //String userName = dataSnapshot.child("name").getValue().toString();
                //String userEmail = dataSnapshot.child("email").getValue().toString();

                String userName = user.getName();
                String userEmail = user.getEMail();

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
        if (item.getItemId() == R.id.menuHomeConnectedUserItem) {
            //login clicked
            Log.d(TAG, "onUserScreenClicked in Activity");

            return true;
        } else if (item.getItemId() == R.id.menuHomeConnectedChatItem) {
            //register clicked
            Log.d(TAG, "onChatClicked in Activity");

            //launch the chatActivity
            Intent intent = new Intent(UserActivity.this, ChatActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_connected, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
