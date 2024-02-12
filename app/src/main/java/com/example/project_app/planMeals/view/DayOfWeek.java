package com.example.project_app.planMeals.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.randomMeal.view.RandomMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DayOfWeek extends AppCompatActivity {
    private ListView listViewNames;
    private ArrayAdapter<String> adapter;
    private String[] names = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday","Thursday","Friday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_of_week);
        listViewNames = findViewById(R.id.dayListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);

        listViewNames.setAdapter(adapter);
        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked day
                String clickedDay = adapter.getItem(position);

                // Show a toast with the day's name
                if (clickedDay != null) {

                    String message = "You clicked on " + clickedDay;
                    Toast.makeText(DayOfWeek.this, message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PlanActivity.class);
                    intent.putExtra("day", clickedDay);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                }
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottomplan);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.bottomplan){
                return true;
            }
            else if (item.getItemId()==R.id.bottomhome) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }
            else if(item.getItemId()==R.id.buttom_dashboard) {
                startActivity(new Intent(getApplicationContext(), RandomMealActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }
            else if(item.getItemId()==R.id.bottom_fav) {
                startActivity(new Intent(getApplicationContext(), FavActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }
            else {
                return false;
            }
        });
    }
}