package com.example.mgenty.mrrobot_android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mgenty.mrrobot_android_project.chat.ChatFragment;
import com.example.mgenty.mrrobot_android_project.user.UserFragment;

public class ChatActivity extends AppCompatActivity implements ChatFragment.ChatListener{
    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.chatContainer, new ChatFragment())
                    .commit();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHomeConnectedUserItem) {
            //login clicked
            Log.d(TAG, "onUserScreenClicked in Activity");

            //launch the chatActivity
            Intent intent = new Intent(ChatActivity.this, UserActivity.class);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.menuHomeConnectedChatItem) {
            //register clicked
            Log.d(TAG, "onChatClicked in Activity");


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
