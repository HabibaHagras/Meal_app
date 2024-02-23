package com.example.project_app.favMeals.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_app.Auth.AuthActivity;
import com.example.project_app.Auth.LoginActivity;
import com.example.project_app.Auth.SearchByActivity;
import com.example.project_app.IteamMeal.view.IteamMealActivity;
import com.example.project_app.MainActivity;
import com.example.project_app.R;
import com.example.project_app.databinding.ActivityFavBinding;
import com.example.project_app.databinding.ActivityRandomMealBinding;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.favMeals.presenter.FavPresenter;
import com.example.project_app.favMeals.presenter.FavPresenterIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.plan.view.Day_PlanActivity;
import com.example.project_app.randomMeal.view.RandomMealActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements OnClickFavListener, AllFavView {
    RecyclerView favRecyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    FavAdapter favproductAdapter;
    AppDataBase dp;
    private DatabaseReference userFavoriteRef;

    MealDAO DAO;
    FavPresenter favPresenter;
    private ActivityFavBinding binding;
    String currentUserEmail;
    Meal  mael;
    private FirebaseAuth auth;
    private static final String TAG = "MealLocalDataSourceIm";

    Button sync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_fav);
        sync=findViewById(R.id.sync_button);
        auth = FirebaseAuth.getInstance();
        currentUserEmail = getIntent().getStringExtra("currentUserEmail");
        SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
        String email= sp.getString("userEmail","");
        favRecyclerView=findViewById(R.id.rv_fav);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        favRecyclerView.setLayoutManager(linearLayoutManager);
        mealClient=MealClient.getInstance();
        favproductAdapter = new FavAdapter(this, new ArrayList<>(),this);
        favRecyclerView.setAdapter(favproductAdapter);
        favRecyclerView.setLayoutManager(linearLayoutManager);
        dp=AppDataBase.getInstance(this);
        auth = FirebaseAuth.getInstance();

        // favproductAdapter.SetList( repo.getStoredProduct());
        favproductAdapter.notifyDataSetChanged();
        favPresenter=new FavPresenterIm(this, mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)));
        Log.i(TAG, "favPresenter: "+"   "+email);

        favPresenter.getMeal();
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = auth.getCurrentUser();
//        userFavoriteRef = FirebaseDatabase.getInstance().getReference("userFavorites")
//                .child(auth.getCurrentUser().getUid());
//        if (currentUser != null) {
//            String uidFromCurrentDevice = currentUser.getUid();
//            userFavoriteRef = FirebaseDatabase.getInstance().getReference("userFavorites").child(uidFromCurrentDevice);
//           fetchFavoriteMeals();
//        } else {
//            // Handle the case where the user is not authenticated
//        }

//        LiveData<List<Meal>> productList=DAO.getFavoriteMealsForUser(email);
//        productList.observe(this, new Observer<List<Meal>>() {
//            @Override
//            public void onChanged(List<Meal> meals) {
//                favproductAdapter.SetList(meals);
//                favproductAdapter.notifyDataSetChanged();
//
//            }
//        });
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSyncClick();

        }});


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_fav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.bottom_fav){
                return true;
            }
            else if (item.getItemId() == R.id.bottomlogout) {
                auth.signOut();
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("userEmail");
                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
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
            else if(item.getItemId()==R.id.planbotton) {
                startActivity(new Intent(getApplicationContext(), Day_PlanActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();

                return true;
            }else if (item.getItemId() == R.id.searchbotton) {
                startActivity(new Intent(getApplicationContext(), SearchByActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

                return true;
            }
            else {
                return false;
            }
        });


    }

    @Override
    public void showdata(List<Meal> products) {
//    favPresenter.getMeal();

        favproductAdapter = new FavAdapter(this,products,this);
        favRecyclerView.setAdapter(favproductAdapter);
        favproductAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void deleteProduct(Meal meal) {
        favPresenter.deleteproduct(meal);


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onFavProductClick(Meal meal) {
        Toast.makeText(FavActivity.this,"DELETED",Toast.LENGTH_SHORT).show();
        deleteProduct(meal);
    }

    @Override
    public void OnCartclick(Meal meal) {
        Intent intent = new Intent(getApplicationContext(), IteamMealActivity.class);
        intent.putExtra("MEAL_KEY", meal);
        startActivity(intent);
    }

    @Override
    public void OnSyncClick() {
        if (auth.getCurrentUser() != null) {
            String uid = auth.getCurrentUser().getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("userFavorites").child(uid);
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Meal> favoriteMeals = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        // Assuming Meal class has a constructor that accepts a DataSnapshot
                        Meal meal = dataSnapshot.getValue(Meal.class);

                        if (meal != null) {
                            favoriteMeals.add(meal);
                        }
                    }

                    // Update your RecyclerView with the retrieved favoriteMeals list
                    FavAdapter favAdapter = new FavAdapter(FavActivity.this, favoriteMeals, FavActivity.this);
                    favRecyclerView.setAdapter(favAdapter);
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "Failed to read userFavorites", error.toException());
                }
            });
        }
    }
    }

