package com.example.project_app.IteamMeal.view;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_app.Day.view.DayActivity;
import com.example.project_app.IteamMeal.presenter.IteamMealPresenterIm;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.model.Category;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealRemoteDataSourceIm;
import com.example.project_app.randomMeal.presenter.AllMealPresenterIm;
import com.example.project_app.randomMeal.view.AllMealView;
import com.example.project_app.randomMeal.view.RandomMealActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.project_app.IteamMeal.presenter.IteamMealPresenter;
import com.example.project_app.R;
import com.example.project_app.model.Meal;
import com.example.project_app.randomMeal.presenter.AllMealPresenter;
import com.example.project_app.randomMeal.view.AreaAdapter;
import com.example.project_app.randomMeal.view.PutInFavListener;
import com.example.project_app.randomMeal.view.RycAdapter;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.ArrayList;
import java.util.List;

public class IteamMealActivity extends AppCompatActivity   implements   IteamMealView , onPutFavListener {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    IteamMealAdapter productAdapter;
    IteamMealPresenter allMealPresenter;
    CardView cardView;
    ImageView imageViewMeal;
    TextView textViewMealName;
    TextView textViewCategoryArea;
    TextView textViewIngredients;
    TextView textViewInstructions;
    TextView textViewSource;
    VideoView videoView;
    Meal meal;
    Context context;
    private ExoPlayer player;
    private PlayerView playerView;
    WebView youtube;
    String email;
    Button Favv;
    Button Plann;
    private static final String TAG = "IteamMealActivity";
    String videoURL = "https://video.blender.org/download/videos/3d95fb3d-c866-42c8-9db1-fe82f48ccb95-804.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteam_meal);
        imageViewMeal = findViewById(R.id.imageViewMeal);
        textViewMealName = findViewById(R.id.textViewMealName);
        textViewCategoryArea = findViewById(R.id.textViewCategoryArea);
        textViewIngredients = findViewById(R.id.textViewIngredients);
        textViewInstructions = findViewById(R.id.textViewInstructions);
        textViewSource = findViewById(R.id.textViewSource);
        youtube = findViewById(R.id.webView);
        Favv=findViewById(R.id.favMeal_button);
        Plann=findViewById(R.id.olanMeal_button);
        Intent intent = getIntent();
        meal = (Meal) intent.getSerializableExtra("MEAL_KEY");
        SharedPreferences sp = getApplicationContext().getSharedPreferences("useremail", Context.MODE_PRIVATE);
        email= sp.getString("userEmail","");
        String youtubeUrl = meal.getStrYoutube();
        if (youtubeUrl != null) {
            String videoUrl = youtubeUrl.replace("watch?v=", "embed/");
            String video = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
            youtube.loadData(video, "text/html", "utf-8");
            youtube.getSettings().setJavaScriptEnabled(true);
            youtube.setWebChromeClient(new WebChromeClient());
        }
        Glide.with(this).load(meal.getStrMealThumb()).into(imageViewMeal);
        textViewMealName.setText(meal.getStrMeal());
        textViewCategoryArea.setText(meal.getStrCategory());

        StringBuilder ingredientsBuilder = new StringBuilder("Ingredients: ");
        for (int i = 1; i <= 20; i++) {
            String ingredient = getIngredient(meal, i);
            if (ingredient != null && !ingredient.isEmpty()) {
                ingredientsBuilder.append(ingredient).append(", ");
            }
        }
        if (ingredientsBuilder.length() > "Ingredients: ".length()) {
            ingredientsBuilder.setLength(ingredientsBuilder.length() - 2);
        } else {
            textViewIngredients.setVisibility(View.GONE);
        }

        textViewIngredients.setText(ingredientsBuilder.toString());
        textViewInstructions.setText("Instructions: " + meal.getStrInstructions());
        textViewSource.setText("Source: " + meal.getStrSource());


        allMealPresenter = new IteamMealPresenterIm(this,
                mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                        MealLocalDataSourceIm.getInstance(this)
                ));
        Favv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+"Meallllllllllllllllll");
               oPutInFavClick(meal);

            }
        });
        Plann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               OnPlanClick(meal);

            }
        });

    }

    private String getIngredient(Meal meal, int index) {
        String ingredientFieldName = "strIngredient" + index;
        try {
            java.lang.reflect.Field ingredientField = meal.getClass().getDeclaredField(ingredientFieldName);
            ingredientField.setAccessible(true);
            String ingredient = (String) ingredientField.get(meal);
            if (ingredient != null && !ingredient.isEmpty()) {
                return ingredient;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.setPlayWhenReady(false);
            player.release();
            player = null;
        }


    }

    @Override
    public void showdata(List<Meal> products) {

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void addProduct(Meal product) {
        allMealPresenter.addtoFav(product);

    }


    @Override
    public void oPutInFavClick(Meal meal) {
        meal.setUserEmail(email);
        Toast.makeText(IteamMealActivity.this,"added",Toast.LENGTH_SHORT).show();
        addProduct(meal);
    }

    @Override
    public void OnPlanClick(Meal meal) {
        Toast.makeText(IteamMealActivity.this,"pls choose day",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), DayActivity.class);
        intent.putExtra("MEAL_OBJECT_KEY", meal);
        startActivity(intent);
    }


}