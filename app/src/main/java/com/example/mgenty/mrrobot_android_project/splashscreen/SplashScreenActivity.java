package com.example.mgenty.mrrobot_android_project.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mgenty.mrrobot_android_project.HomeActivity;
import com.example.mgenty.mrrobot_android_project.R;
import com.tozny.crypto.android.AesCbcWithIntegrity;

import static com.example.mgenty.mrrobot_android_project.crypto.Crypto.*;

public class SplashScreenActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}