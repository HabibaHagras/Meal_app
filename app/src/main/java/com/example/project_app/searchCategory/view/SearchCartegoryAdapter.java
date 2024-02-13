package com.example.project_app.searchCategory.view;

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
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.search.view.SearchAdapter;
import com.example.project_app.search.view.onClickSearchListener;

import java.util.ArrayList;
import java.util.List;

public class SearchCartegoryAdapter extends  RecyclerView.Adapter<SearchCartegoryAdapter.MyViewHolder> {
    Context context;
    List<Meal> meals;
    private OnclickSearchCaregory listner;

    public SearchCartegoryAdapter(Context context, List<Meal> meals, OnclickSearchCaregory listner) {
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
    public SearchCartegoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_search_category,parent,false);
        SearchCartegoryAdapter.MyViewHolder myViewHolder = new SearchCartegoryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCartegoryAdapter.MyViewHolder holder, int position) {
        Meal product = meals.get(position);
        holder.tvTitle.setText("Title of Meal :  " +meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView img;
        ImageButton Delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.Name_of_searchCategory_meal);
            img=itemView.findViewById(R.id.imageViewsearchCategory);

        }
    }}


