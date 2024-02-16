package com.example.project_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.example.project_app.Auth.AuthActivity;
import com.example.project_app.randomMeal.view.RandomMealActivity;


public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView=findViewById(R.id.animationView);
        sp = getSharedPreferences("useremail", MODE_PRIVATE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String username = sp.getString("userEmail", "");
                Log.i("TAG", "run: "+ "RECIEVED User Email: " + username);
                Intent intent;
                if (!username.isEmpty()) {
                    intent = new Intent(SplashScreen.this, RandomMealActivity.class);
                } else {
                    intent = new Intent(SplashScreen.this, AuthActivity.class);
                }           //     Intent i = new Intent(SplashScreen.this, AuthActivity.class);

                // on below line we are
                // starting a new activity.
                startActivity(intent);

                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 9000);


    }
}