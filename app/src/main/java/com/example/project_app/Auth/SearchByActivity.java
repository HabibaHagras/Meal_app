package com.example.project_app.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_app.R;
import com.example.project_app.search.view.SearchActivity;
import com.example.project_app.searchCategory.view.SearchCategoryActivity;

public class SearchByActivity extends AppCompatActivity {
    Button meal;
    Button cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by);
        meal=findViewById(R.id.meal);
        cat=findViewById(R.id.Category);
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
            }
        });
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchCategoryActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
            }
        });
    }
}