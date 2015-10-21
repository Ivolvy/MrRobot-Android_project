package com.example.mgenty.mrrobot_android_project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.mgenty.mrrobot_android_project.user.User;
import com.example.mgenty.mrrobot_android_project.user.UserFragment;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class UserActivity extends AppCompatActivity implements UserFragment.UserListener, NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "UserActivity";
    public static final String EXTRA_USER_ID = "com.example.mgenty.mrrobot_android_project.EXTRA_USER_ID";
    private Firebase mFirebaseRef;
    private UserFragment userFragment;
    private String userId;


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
        userId = intent.getStringExtra(HomeActivity.EXTRA_USER_ID);

        //load user information in order to fill the interface
        loadUserInformations(userId);


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        if (item.getItemId() == R.id.menuHomeScreenItem) {
            //login clicked
            Log.d(TAG, "onHomeScreenClicked in Activity");

            return true;
        } else if (item.getItemId() == R.id.menuHomeAboutItem) {
            //register clicked
            Log.d(TAG, "onAboutClicked in Activity");

            return true;
        }
        else if (item.getItemId() == R.id.menuHomeConnectedLogOutItem) {
            //register clicked
            Log.d(TAG, "onLogOutClicked in Activity");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_connected, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_communication) {
            Log.d(TAG, "onChatClicked in SlideMenu");
            //launch the chatActivity
            Intent intent = new Intent(UserActivity.this, ChatActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sendMessages) {

        } else if (id == R.id.nav_receiveMessages) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_userAccount) {
            onBackPressed();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
