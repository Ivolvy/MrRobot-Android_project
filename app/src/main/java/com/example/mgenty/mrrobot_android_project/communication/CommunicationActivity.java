package com.example.mgenty.mrrobot_android_project.communication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mgenty.mrrobot_android_project.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerViewAdapter;

import java.util.Random;

public class CommunicationActivity extends AppCompatActivity implements ListFragment.ListListener{
    private static final String TAG = "CommunicationActivity";
    private ListFragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        //load UserFragment
        listFragment = new ListFragment();

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.communicationContainer, listFragment)
                    .commit();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
