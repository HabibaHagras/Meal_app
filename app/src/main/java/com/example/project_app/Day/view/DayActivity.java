package com.example.project_app.Day.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project_app.Day.presenter.DayPresenter;
import com.example.project_app.Day.presenter.DayPresenterIm;
import com.example.project_app.R;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealRemoteDataSourceIm;

public class DayActivity extends AppCompatActivity implements PutInPlanDaywithMealListener ,AllDayView {
    private ListView listViewNames;
    private static final String TAG = "DayActivity";
    private ArrayAdapter<String> adapter;
    private String[] names = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    DayPresenter dayPresenter;
    Meal meallll;
    String clickedDay;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
             meallll = (Meal) intent.getSerializableExtra("MEAL_OBJECT_KEY");
        dayPresenter= new DayPresenterIm(this, mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),MealLocalDataSourceIm.getInstance(this)));
        SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
        email= sp.getString("userEmail","");
        setContentView(R.layout.activity_day);
        listViewNames = findViewById(R.id.dayListView);
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
                    Toast.makeText(DayActivity.this, message, Toast.LENGTH_SHORT).show();
                    OnPlanClick();
                }
            }
        });
    }

    @Override
    public void OnPlanClick() {
        addProduct(meallll,clickedDay);
    }

    @Override
    public void addProduct(Meal product, String day) {
        product.setDay(day);
        product.setUserEmail(email);
        Log.i(TAG, "addProduct: "+"   "+day);
        dayPresenter.addtoPlan(product,day);
    }
}