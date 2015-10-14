package com.example.mgenty.mrrobot_android_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mgenty.mrrobot_android_project.home.HomeFragment;
import com.example.mgenty.mrrobot_android_project.user.LoginFragment;
import com.example.mgenty.mrrobot_android_project.user.RegisterFragment;
import com.example.mgenty.mrrobot_android_project.user.User;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements HomeFragment.HomeListener, LoginFragment.LoginListener, RegisterFragment.RegisterListener{
    private static final String TAG = "HomeActivity";
    public static final String EXTRA_USER_ID = "com.example.mgenty.mrrobot_android_project.EXTRA_USER_ID";
    private Firebase mFirebaseRef;
    private Toolbar mToolbar;
    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://androidmrrobot.firebaseio.com/");

        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.homeContainer, new HomeFragment())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuHomeLoginItem) {
            //login clicked
            Log.d(TAG, "onLoginClicked in Activity");

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homeContainer, new LoginFragment())
                    .commit();

            return true;
        } else if (item.getItemId() == R.id.menuHomeRegisterItem) {
            //register clicked
            Log.d(TAG, "onRegisterClicked in Activity");

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homeContainer, new RegisterFragment())
                    .commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onLoginClicked(CharSequence loginEmail, CharSequence loginPassword) {
        Log.d(TAG, "onHomeClicked in Activity");

        final String mEmail = loginEmail.toString();
        final String mPassword = loginPassword.toString();
        logUser(mEmail, mPassword);
    }

    public void logUser(String email, String password){
        Log.d(TAG, "logUser function");
        mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                //launch the userActivity
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                String userId = authData.getUid();
                intent.putExtra(EXTRA_USER_ID, userId);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                //there was an error
            }
        });
    }

    @Override
    public void onRegisterClicked(CharSequence registerName, CharSequence registerPassword, CharSequence registerEmail) {
        Log.d(TAG, "onHomeRegisterClicked in Activity");

        final String mEmail = registerEmail.toString();
        final String mPassword = registerPassword.toString();
        final String mName = registerName.toString();

        mFirebaseRef.createUser(mEmail, mPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));

//                Map<String, String> userAccount = new HashMap<String, String>();
//                userAccount.put("name", mName);
//                userAccount.put("email", mEmail);
//                userAccount.put("password", mPassword);
//                mFirebaseRef.child("users").child((String) result.get("uid")).setValue(userAccount);

                Firebase userRef = mFirebaseRef.child("users").child(result.get("uid").toString());
                user = new User(mName, mEmail, mPassword);
                userRef.setValue(user);

                logUser(mEmail, mPassword);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    public static User getUser(){
        return user;
    }


}
