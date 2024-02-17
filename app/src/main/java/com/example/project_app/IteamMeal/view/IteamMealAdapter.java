package com.example.project_app.IteamMeal.view;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_app.R;
import com.example.project_app.model.Meal;
import com.example.project_app.randomMeal.view.AreaAdapter;
import com.example.project_app.randomMeal.view.PutInFavListener;
import com.example.project_app.randomMeal.view.RycAdapter;

import java.util.List;

public class IteamMealAdapter  extends RecyclerView.Adapter<IteamMealAdapter.MyViewHolder>{

    Context context;
    List<Meal> products;
    private onPutFavListener listner;
    private static final String TAG = "IteamMealAdapter";

    public IteamMealAdapter(Context context, List<Meal> products, onPutFavListener listner) {
        this.context = context;
        this.products = products;
        this.listner = listner;
    }
    public  void SetList(List<Meal>updateProducts){
        this.products=updateProducts;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public IteamMealAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_iteam_meal,parent,false);
        IteamMealAdapter.MyViewHolder myViewHolder = new IteamMealAdapter.MyViewHolder(view);
        return myViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull IteamMealAdapter.MyViewHolder holder, int position) {
        Meal meal = products.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageViewMeal);
        holder.textViewMealName.setText(meal.getStrMeal());
        holder.textViewCategoryArea.setText(meal.getStrCategory());  // Set your actual data
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
            holder.textViewIngredients.setVisibility(View.GONE);
        }
        holder.textViewIngredients.setText(ingredientsBuilder.toString());      holder.textViewInstructions.setText("Instructions: " + meal.getStrInstructions());  // Set your actual data
        holder.textViewSource.setText("Source: " + meal.getStrSource());
        holder.videoView.setVideoURI(Uri.parse(meal.getStrYoutube()));
        holder.Favv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+"Meallllllllllllllllll");
                listner.oPutInFavClick(meal);

            }
        });
        holder.Plann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.OnPlanClick(meal);

            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageViewMeal;
        TextView textViewMealName;
        TextView textViewCategoryArea;
        TextView textViewIngredients;
        TextView textViewInstructions;
        TextView textViewSource;
        VideoView videoView;
        Button Favv;
        Button Plann;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageViewMeal = itemView.findViewById(R.id.imageViewMeal);
            textViewMealName = itemView.findViewById(R.id.textViewMealName);
            textViewCategoryArea = itemView.findViewById(R.id.textViewCategoryArea);
            textViewIngredients = itemView.findViewById(R.id.textViewIngredients);
            textViewInstructions = itemView.findViewById(R.id.textViewInstructions);
            textViewSource = itemView.findViewById(R.id.textViewSource);
            videoView = itemView.findViewById(R.id.videoView);
            Favv=itemView.findViewById(R.id.favMeal_button);
            Plann=itemView.findViewById(R.id.olanMeal_button);
        }
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

}
