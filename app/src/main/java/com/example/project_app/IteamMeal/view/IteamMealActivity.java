package com.example.project_app.IteamMeal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
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

public class IteamMealActivity extends AppCompatActivity   {
//    RecyclerView recyclerView;
//    LinearLayoutManager linearLayoutManager;
//    IteamMealAdapter productAdapter;
//    IteamMealPresenter allMealPresenter;
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
    String videoURL="https://video.blender.org/download/videos/3d95fb3d-c866-42c8-9db1-fe82f48ccb95-804.mp4";
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
        Intent intent = getIntent();
        meal = (Meal) intent.getSerializableExtra("MEAL_KEY");
        String videoUrl = meal.getStrYoutube().replace("watch?v=", "embed/");
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"" + videoUrl + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        youtube.loadData(video, "text/html","utf-8");
        youtube.getSettings().setJavaScriptEnabled(true);
        youtube.setWebChromeClient(new WebChromeClient());
        Glide.with(this).load(meal.getStrMealThumb()).into(imageViewMeal);
        textViewMealName.setText(meal.getStrMeal());
        textViewCategoryArea.setText( meal.getStrCategory());

        StringBuilder ingredientsBuilder = new StringBuilder("Ingredients: ");
        for (int i = 1; i <= 20; i++) {
            String ingredient = getIngredient(meal, i);
            if (ingredient != null && !ingredient.isEmpty()) {
                ingredientsBuilder.append(ingredient).append(", ");
            }
        }

        // Remove the trailing comma and space if there are ingredients
        if (ingredientsBuilder.length() > "Ingredients: ".length()) {
            ingredientsBuilder.setLength(ingredientsBuilder.length() - 2);
        } else {
            // No ingredients, hide the TextView
            textViewIngredients.setVisibility(View.GONE);
        }

        textViewIngredients.setText(ingredientsBuilder.toString());
        textViewInstructions.setText("Instructions: " + meal.getStrInstructions());
        textViewSource.setText("Source: " + meal.getStrSource());





    }
    private String getIngredient(Meal meal, int index) {
        // The ingredient methods in Meal are named strIngredient1, strIngredient2, ..., strIngredient20
        String ingredientFieldName = "strIngredient" + index;

        // Use reflection to get the value of the ingredient field dynamically
        try {
            java.lang.reflect.Field ingredientField = meal.getClass().getDeclaredField(ingredientFieldName);
            ingredientField.setAccessible(true);
            String ingredient = (String) ingredientField.get(meal);

            // Check if the ingredient is not null and not empty
            if (ingredient != null && !ingredient.isEmpty()) {
                return ingredient;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // Return null if the ingredient is null or empty
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

//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        productAdapter=new IteamMealAdapter(this, new ArrayList<>(),this);
//        recyclerView.setAdapter(productAdapter);
//
//        allMealPresenter.getMeal(meal);

    }

//    @Override
//    public void showdata(List<Meal> products) {
//        productAdapter.SetList(products);
//        productAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void showErrorMsg(String error) {
//
//    }
//
//    @Override
//    public void oPutInFavClick(Meal meal) {
//
//    }
//
//    @Override
//    public void OnPlanClick(Meal meal) {
//
//    }
//
//    @Override
//    public void OnCartclick(Meal meal) {
//
//    }
