package com.example.mgenty.mrrobot_android_project.communication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;

import com.example.mgenty.mrrobot_android_project.ChatActivity;
import com.example.mgenty.mrrobot_android_project.HomeActivity;
import com.example.mgenty.mrrobot_android_project.R;
import com.example.mgenty.mrrobot_android_project.crypto.Secret;

public class CommunicationActivity extends AppCompatActivity implements ListFragment.ListListener{
    private static final String TAG = "CommunicationActivity";
    private ListFragment listFragment;

    private String mPassword = "";
    protected static String chatMode ="";

    private Bundle mSavedInstanceState;

    private static Secret mSecret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;

        Intent intent = getIntent();
        chatMode = intent.getStringExtra(ChatActivity.EXTRA_MODE);

        setContentView(R.layout.activity_communication);

        enterPassword();
    }

    public void enterPassword() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your password");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSecret = new Secret(input.getText().toString());
                loadFragment();
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                onBackPressed();
            }
        });

        builder.show();
    }

    public void loadFragment() {
        //load UserFragment
        listFragment = new ListFragment();

        if(mSavedInstanceState == null){
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

    public static Secret getSecret(){
        return mSecret;
    }
    public static void setSecret(Secret secret){
        mSecret = secret;
    }

}
