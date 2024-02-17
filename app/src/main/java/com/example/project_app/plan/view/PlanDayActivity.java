package com.example.project_app.plan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project_app.R;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.favMeals.presenter.FavPresenter;
import com.example.project_app.favMeals.presenter.FavPresenterIm;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.favMeals.view.FavAdapter;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.plan.presenter.PlanPresenter;
import com.example.project_app.plan.presenter.PlanPresenterIm;

import java.util.ArrayList;
import java.util.List;

public class PlanDayActivity extends AppCompatActivity implements  OnClickPlanListener,AllPlanView{
    RecyclerView planRecyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    PlanAdapter planAdapter;
    AppDataBase dp;
    MealDAO DAO;
    PlanPresenter planPresenter;
    String day;
    private static final String TAG = "PlanDayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_day);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
        String email= sp.getString("userEmail","");
        day = getIntent().getStringExtra("dayy");

        planRecyclerView=findViewById(R.id.rv_Plan);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        planRecyclerView.setLayoutManager(linearLayoutManager);
        mealClient=MealClient.getInstance();
        planAdapter = new PlanAdapter(this, new ArrayList<>(),this);
        planRecyclerView.setAdapter(planAdapter);
        planRecyclerView.setLayoutManager(linearLayoutManager);
        dp=AppDataBase.getInstance(this);
        DAO=dp.getmealDAO();
        planAdapter.notifyDataSetChanged();
       planPresenter=new PlanPresenterIm(this, mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)));
        planPresenter.getPlan();

//        LiveData<List<Meal>> productList=DAO.getFavoriteMealsPlansForUser(email,day);
//        productList.observe(this, new Observer<List<Meal>>() {
//            @Override
//            public void onChanged(List<Meal> meals) {
//                Log.i(TAG, "onChanged: hereeeeeeeeeeeer");
//                planAdapter.SetList(meals);
//                planAdapter.notifyDataSetChanged();
//
//            }
//        });

    }

    @Override
    public void showdata(List<Meal> products) {
    //    planPresenter.getPlan();

        planAdapter = new PlanAdapter(this,products,this);
        planRecyclerView.setAdapter(planAdapter);
        planAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void deleteProduct(Meal meal) {
        planPresenter.deleteproductPlan(meal);

    }

    @Override
    public String getDayFromIntent() {
        return getIntent().getStringExtra("dayy");

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onPlanProductClick(Meal meal) {
        Toast.makeText(PlanDayActivity.this,"DELETED",Toast.LENGTH_SHORT).show();
        deleteProduct(meal);

    }
}