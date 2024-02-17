package com.example.project_app.plan.view;

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
import com.example.project_app.favMeals.view.FavAdapter;
import com.example.project_app.favMeals.view.OnClickFavListener;
import com.example.project_app.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter  extends  RecyclerView.Adapter<PlanAdapter.MyViewHolder>{
    Context context;
    List<Meal> meals;
    private OnClickPlanListener listner;

    public PlanAdapter(Context context, List<Meal> meals, OnClickPlanListener listner) {
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
    public PlanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_plan,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.MyViewHolder holder, int position) {
        Meal product = meals.get(position);
        holder.tvTitle.setText("Title of Meal :  " +meals.get(position).getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.img);
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onPlanProductClick(product);

            }
        });
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
        return meals.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView img;
        ImageButton Delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.Name_of_plan_meal);
            img=itemView.findViewById(R.id.imageViewplan);
            Delete=itemView.findViewById(R.id.delete_plan_button);

        }
    }}

