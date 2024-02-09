package com.example.project_app.favMeals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_app.R;
import com.example.project_app.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class  FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder>  {


    Context context;
    List<Meal> meals;
    private OnClickFavListener listner;

    public FavAdapter(Context context, List<Meal> meals ,OnClickFavListener _lister){
        this.context = context;
        this.meals = meals;
        this.listner=_lister;
        meals=new ArrayList<Meal>();

    }
    public  void SetList(List<Meal>updateProducts){
        this.meals=updateProducts;


    }

    @NonNull
    @Override
    public FavAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fav_meal,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal product = meals.get(position);
        holder.tvTitle.setText("Title of Meal :  " +meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.img);
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onFavProductClick(product);

            }
        });


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvPrice;
        TextView tvBrand;
        TextView tvDesc;
        ImageView img;
        TextView ratingBar;
        ImageButton Delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.Name_of_fav_meal);
            img=itemView.findViewById(R.id.imageViewfav);
            Delete=itemView.findViewById(R.id.delete_fav_button);

        }
    }}


