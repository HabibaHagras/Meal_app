package com.example.project_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DayActivity extends AppCompatActivity {
    private ListView listViewNames;
    private ArrayAdapter<String> adapter;
    private String[] names = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
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
                    Toast.makeText(DayActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}