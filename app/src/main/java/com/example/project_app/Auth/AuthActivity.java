package com.example.project_app.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.randomMeal.view.RandomMealActivity;

public class AuthActivity extends AppCompatActivity {
    Button login ;
    Button signup;
    Button signin;
    Button Skip ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        login=findViewById(R.id.LoginButton);
        signup=findViewById(R.id.SignupButton);
        signin=findViewById(R.id.containedButton);
        Skip=findViewById(R.id.Skipbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));

            }
        });
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this, RandomMealActivity.class);
                intent.putExtra("skip", true);
                startActivity(intent);
            }
        });
    }
}