package com.example.mgenty.mrrobot_android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mgenty.mrrobot_android_project.chat.ChatFragment;
import com.example.mgenty.mrrobot_android_project.chat.ReceivedMessageFragment;
import com.example.mgenty.mrrobot_android_project.chat.SendMessageFragment;
import com.example.mgenty.mrrobot_android_project.communication.CommunicationActivity;
import com.example.mgenty.mrrobot_android_project.user.User;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity implements ChatFragment.ChatListener, SendMessageFragment.SendListener, ReceivedMessageFragment.ReceivedListener, NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "ChatActivity";
    private Firebase mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://androidmrrobot.firebaseio.com/");

        setContentView(R.layout.activity_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.chatContainer, new ChatFragment())
                    .commit();
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHomeScreenItem) {
            //login clicked
            Log.d(TAG, "onHomeScreenClicked in Activity");

            return true;
        } else if (item.getItemId() == R.id.menuHomeAboutItem) {
            Log.d(TAG, "onAboutClicked in Activity");

            return true;
        }
        else if (item.getItemId() == R.id.menuHomeConnectedLogOutItem) {
            Log.d(TAG, "onLogOutClicked in Activity");
            mFirebaseRef.unauth();
            Intent intent = new Intent(ChatActivity.this, HomeActivity.class);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_communication) {

        } else if (id == R.id.nav_sendMessages) {

        } else if (id == R.id.nav_receiveMessages) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_userAccount) {
            onBackPressed();
        } else if (id == R.id.nav_disconnect) {
            mFirebaseRef.unauth();
            Intent intent = new Intent(ChatActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onAccessSendMessageClicked() {
        Log.d(TAG, "onAccessMessageClicked in Activity");

        //launch the chatActivity
        Intent intent = new Intent(ChatActivity.this, CommunicationActivity.class);
        startActivity(intent);

        //old chat method
     /*   getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.chatContainer, new SendMessageFragment())
                .commit();*/

    }

    public void onSendMessageClicked(String message) {
        Log.d(TAG, "onSendMessageClicked in Activity");

        Map<String, String> messages = new HashMap<String, String>();

        //retrieve the current User
        User user = HomeActivity.getUser();

        messages.put("sender", user.getName());
        messages.put("receiver", "lui");
        messages.put("message", message);
        mFirebaseRef.child("messages").push().setValue(messages);

    }

}
