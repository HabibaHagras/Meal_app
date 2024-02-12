package com.example.project_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.plan.view.Day_PlanActivity;
import com.example.project_app.randomMeal.view.RandomMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.project_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String currentUserEmail;
    Boolean isUserLoggedIn;
    Boolean skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        currentUserEmail = getIntent().getStringExtra("currentUserEmail");
        skip = getIntent().getBooleanExtra("skip",false);
        TextView userEmailTextView = findViewById(R.id.userEmailTextView);
        userEmailTextView.setText("Welcome, " + currentUserEmail);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.getMenu().clear();
        if (skip==true){
                    bottomNavigationView.inflateMenu(R.menu.guest_menu);
            bottomNavigationView.setSelectedItemId(R.id.buttom_dashboardguest);
            bottomNavigationView.setOnItemSelectedListener(item -> {
//                if (item.getItemId() == R.id.bottomhome) {
//                    return true;
//                }   else
                    if (item.getItemId() == R.id.buttom_dashboardguest) {
                    // startActivity(new Intent(getApplicationContext(), RandomMealActivity.class));
                    Intent intent = new Intent(getApplicationContext(), RandomMealActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;

                }
                else {
                    return false;
                }
            });
        }
        else {
            bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu);

            bottomNavigationView.setSelectedItemId(R.id.bottomhome);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.bottomhome) {
                    return true;
                } else if (item.getItemId() == R.id.buttom_dashboard) {
                    // startActivity(new Intent(getApplicationContext(), RandomMealActivity.class));
                    Intent intent = new Intent(getApplicationContext(), RandomMealActivity.class);
                    intent.putExtra("currentUserEmail", currentUserEmail);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.bottom_fav) {
                    startActivity(new Intent(getApplicationContext(), FavActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.planbotton) {
                    startActivity(new Intent(getApplicationContext(), Day_PlanActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else {
                    return false;
                }
            });
        }
////
////        binding = ActivityMainBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}