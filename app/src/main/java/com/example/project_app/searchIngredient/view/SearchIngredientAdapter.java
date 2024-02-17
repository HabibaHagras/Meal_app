package com.example.project_app.searchIngredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_app.R;
import com.example.project_app.model.Meal;
import com.example.project_app.searchCategory.view.OnclickSearchCaregory;
import com.example.project_app.searchCategory.view.SearchCartegoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.MyViewHolder> {

    Context context;
    List<Meal> meals;
    private onClickSearchIngredient listner;

    public SearchIngredientAdapter(Context context, List<Meal> meals, onClickSearchIngredient listner) {
        this.context = context;
        this.meals = meals;
        this.listner = listner;
        meals=new ArrayList<Meal>();

    }
    public  void SetList(List<Meal>updateProducts){
        this.meals=updateProducts;


    }
    @NonNull
    @Override
    public SearchIngredientAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_search_ingrediant,parent,false);
        SearchIngredientAdapter.MyViewHolder myViewHolder = new SearchIngredientAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchIngredientAdapter.MyViewHolder holder, int position) {
        Meal product = meals.get(position);
        holder.tvTitle.setText("Title of Meal :  " +meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the listener method for the card view click
                listner.OnCartclick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals != null ? meals.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tvTitle;
    ImageView img;
    ImageButton Delete;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.Name_of_searchIngrediant_meal);
        img=itemView.findViewById(R.id.imageViewsearchIngrediant);

    }
}}


