package com.example.project_app.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_app.R;
import com.example.project_app.databinding.ActivityRandomMealBinding;
import com.example.project_app.databinding.ActivitySearchByBinding;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.plan.view.Day_PlanActivity;
import com.example.project_app.search.view.SearchActivity;
import com.example.project_app.searchArea.view.AreaSearchActivity;
import com.example.project_app.searchCategory.view.SearchCategoryActivity;
import com.example.project_app.searchIngredient.view.SearchIngredientActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchByActivity extends AppCompatActivity {
    Button meal;
    Button cat;
    Button Ingredient;
    Button Area;
    private ActivitySearchByBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchByBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_search_by);
        meal=findViewById(R.id.meal);
        cat=findViewById(R.id.Category);
        Ingredient=findViewById(R.id.Ingredient);
        Area=findViewById(R.id.Area);
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

            }
        });
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchCategoryActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            }
        });
        Ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchIngredientActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            }
        });
        Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AreaSearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            }
        });

    }
}