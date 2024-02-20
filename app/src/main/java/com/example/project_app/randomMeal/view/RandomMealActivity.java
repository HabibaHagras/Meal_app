package com.example.project_app.randomMeal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.project_app.Auth.AuthActivity;
import com.example.project_app.Auth.LoginActivity;
import com.example.project_app.Auth.SearchByActivity;
import com.example.project_app.Day.view.DayActivity;
import com.example.project_app.IteamArea.IteamAreaActivity;
import com.example.project_app.IteamCategory.view.IteamCategoryActivity;
import com.example.project_app.IteamMeal.view.IteamMealActivity;
import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.databinding.ActivityRandomMealBinding;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.favMeals.view.FavActivity;
import com.example.project_app.model.Area;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.plan.view.Day_PlanActivity;
import com.example.project_app.randomMeal.presenter.AllAreaPresenter;
import com.example.project_app.randomMeal.presenter.AllAreaPresenterIm;
import com.example.project_app.randomMeal.presenter.AllCategoryPresenter;
import com.example.project_app.randomMeal.presenter.AllCategoryPresenterIm;
import com.example.project_app.randomMeal.presenter.AllMealPresenter;
import com.example.project_app.randomMeal.presenter.AllMealPresenterIm;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RandomMealActivity extends AppCompatActivity implements   PutInFavListener , AllMealView  ,AllCategoryView ,AllAreaView{
    RecyclerView recyclerView;
    private FirebaseAuth auth;
    RecyclerView recyclerViewofCategory;
    RecyclerView recyclerViewofCountry;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager1;
    LinearLayoutManager linearLayoutManager2;
    MealClient mealClient;
    RycAdapter productAdapter;
    AllMealPresenter allMealPresenter;
    AllCategoryPresenter allCategoryPresenter;
    AllAreaPresenter allAreaPresenter;
    CategoryAdapter categoryAdapter;
    AreaAdapter areaAdapter;
    private ActivityRandomMealBinding binding;
    String currentUserEmail;
    String email;
    Boolean skip;
    SharedPreferences sp;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRandomMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_random_meal);
        currentUserEmail = getIntent().getStringExtra("currentUserEmail");
        SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
         email= sp.getString("userEmail","");
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressBar = findViewById(R.id.progressBar);
        if (auth.getCurrentUser() != null) {
            databaseReference = firebaseDatabase.getInstance().getReference("userFavorites")
                    .child(auth.getCurrentUser().getUid());
        }
        recyclerView = findViewById(R.id.rv_meals);
        recyclerViewofCategory=findViewById(R.id.rv_categories);
        recyclerViewofCountry=findViewById(R.id.rv_countries);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ///////////////////
        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        ////////////////////
        linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        //////////////////
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewofCategory.setLayoutManager(linearLayoutManager1);
        recyclerViewofCountry.setLayoutManager(linearLayoutManager2);
        areaAdapter=new AreaAdapter(this, new ArrayList<>(),this);
        recyclerViewofCountry.setAdapter(areaAdapter);
        mealClient=mealClient.getInstance();
        productAdapter = new RycAdapter(this, new ArrayList<>(),this);
        recyclerView.setAdapter(productAdapter);
        categoryAdapter = new CategoryAdapter(this, new ArrayList<>(),this);
        recyclerViewofCategory.setAdapter(categoryAdapter);
        allMealPresenter= new AllMealPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)
                ));

        allCategoryPresenter=new AllCategoryPresenterIm(this,mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)
        ));
        allAreaPresenter=new AllAreaPresenterIm(this,mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)
        ));
        allMealPresenter.getMeal();
        allCategoryPresenter.getCtegory();
        allAreaPresenter.getArea();
        skip = getIntent().getBooleanExtra("skip",false);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.getMenu().clear();
        if (skip==true){
            bottomNavigationView.inflateMenu(R.menu.guest_menu);
            bottomNavigationView.setSelectedItemId(R.id.buttom_dashboardguest);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.buttom_dashboardguest) {
                    return true;
                }   else
                if (item.getItemId() == R.id.searchguest) {
                    // startActivity(new Intent(getApplicationContext(), RandomMealActivity.class));
                    Intent intent = new Intent(getApplicationContext(), SearchByActivity.class);
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
            bottomNavigationView.setSelectedItemId(R.id.buttom_dashboard);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.buttom_dashboard) {
                    return true;
                } else if (item.getItemId() == R.id.bottomlogout) {
                    auth.signOut();
                    Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.clear(); // Clear all preferences
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.bottom_fav) {
                    //startActivity(new Intent(getApplicationContext(), FavActivity.class));
                    Intent intent = new Intent(getApplicationContext(), FavActivity.class);
                    intent.putExtra("currentUserEmail", currentUserEmail);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();

                    return true;
                } else if (item.getItemId() == R.id.planbotton) {
                    startActivity(new Intent(getApplicationContext(), Day_PlanActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.searchbotton) {
                    startActivity(new Intent(getApplicationContext(), SearchByActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

                    return true;
                }
                else {
                    return false;
                }
            });
        }


    }

    @Override
    public void showdata(List<Meal> products) {
        if (isNetworkAvailable()) {
            productAdapter.SetList(products);
            productAdapter.notifyDataSetChanged();
        }else {
            Intent intent = new Intent(getApplicationContext(), FavActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showErrorMsg(String error) {
        AlertDialog.Builder alertDialog =new AlertDialog.Builder(this);
        alertDialog.setMessage(error).setTitle("Information For You");
        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

    @Override
    public void addProduct(Meal product) {
        allMealPresenter.addtoFav(product);
        String mealKey = databaseReference.push().getKey(); // Generate a unique key
        if (mealKey != null) {
            databaseReference.child(mealKey).setValue(product); // Set the meal under the unique key
            Toast.makeText(RandomMealActivity.this, "Data added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RandomMealActivity.this, "Failed to generate key", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void addPlan(Meal plan_meal) {
        allMealPresenter.addtoPlan(plan_meal);

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void showdataCategory(List<Category> categories) {
        if (isNetworkAvailable()) {
        categoryAdapter.SetList(categories);
        categoryAdapter.notifyDataSetChanged();
        }else {
            Intent intent = new Intent(getApplicationContext(), FavActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void oPutInFavClick(Meal meal) {
        if (email == null || email.trim().isEmpty()) {
            Intent authIntent = new Intent(RandomMealActivity.this, AuthActivity.class);
            startActivity(authIntent);
            finish();}
        else {
            meal.setUserEmail(email);
            meal.setFav(true);
            Toast.makeText(RandomMealActivity.this, "added", Toast.LENGTH_SHORT).show();
            addProduct(meal);
        }
    }

    @Override
    public void OnPlanClick(Meal meal) {
        if (email == null || email.trim().isEmpty()) {
            Intent authIntent = new Intent(RandomMealActivity.this, AuthActivity.class);
            startActivity(authIntent);
            finish();}
        else {
      //  meal.setUserEmail(email);
        Toast.makeText(RandomMealActivity.this,"pls choose day",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), DayActivity.class);
        intent.putExtra("MEAL_OBJECT_KEY", meal);
        startActivity(intent);
       // addProduct(meal);
       // addPlan(meal);
    }}

    @Override
    public void OnCartclick(Meal meal) {
        Intent intent = new Intent(getApplicationContext(), IteamMealActivity.class);
        intent.putExtra("MEAL_KEY", meal);
        startActivity(intent);
    }

    @Override
    public void OnCartCategoryclick(Category category) {
        Intent intent = new Intent(getApplicationContext(), IteamCategoryActivity.class);
        intent.putExtra("Category_KEY",category.getStrCategory());
        startActivity(intent);
    }

    @Override
    public void OnCartAreaclick(Area area) {
        Intent intent = new Intent(getApplicationContext(), IteamAreaActivity.class);
        intent.putExtra("Area_KEY",area.getStrArea());
        startActivity(intent);

    }

    @Override
    public void showdataAreas(List<Area> areas) {
        if (isNetworkAvailable()) {
        areaAdapter.SetList(areas);
        areaAdapter.notifyDataSetChanged();
    }else {
        Intent intent = new Intent(getApplicationContext(), FavActivity.class);
        startActivity(intent);
    }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}