package com.example.project_app.plan.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project_app.Auth.LoginActivity;
import com.example.project_app.Auth.SearchByActivity;
import com.example.project_app.Day.presenter.DayPresenter;
import com.example.project_app.Day.view.DayActivity;
import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.databinding.ActivityDayPlanBinding;
import com.example.project_app.databinding.ActivityRandomMealBinding;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.model.Meal;
import com.example.project_app.randomMeal.view.RandomMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Day_PlanActivity extends AppCompatActivity {
    private ListView listViewNames;
    private static final String TAG = "DayActivity";
    private ArrayAdapter<String> adapter;
    private String[] names = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    DayPresenter dayPresenter;
    Meal meallll;
    String clickedDay;
    String email;
    private FirebaseAuth auth;

    private ActivityDayPlanBinding binding;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityDayPlanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_day_plan);
        listViewNames = findViewById(R.id.rv_Day_Plan);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listViewNames.setAdapter(adapter);
        listViewNames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked day
                clickedDay = adapter.getItem(position);
                // Show a toast with the day's name
                if (clickedDay != null) {
                    String message = "You clicked on " + clickedDay;
                    Toast.makeText(Day_PlanActivity.this, message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PlanDayActivity.class);
                    intent.putExtra("dayy", clickedDay);
                    startActivity(intent);
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.planbotton);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.planbotton) {
                return true;
            } else if (item.getItemId() == R.id.bottomlogout) {
                auth.signOut();
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("userEmail");
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_fav) {
                startActivity(new Intent(getApplicationContext(), FavActivity.class));
               // Intent intent = new Intent(getApplicationContext(), FavActivity.class);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.buttom_dashboard) {
                startActivity(new Intent(getApplicationContext(), RandomMealActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            }else if (item.getItemId() == R.id.searchbotton) {
                startActivity(new Intent(getApplicationContext(), SearchByActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();
                return true;
            } else {
                return false;
            }
        });
    }

}
