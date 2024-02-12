package com.example.project_app.planMeals.view;

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
import com.example.project_app.model.MealPlan;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder>   {
    Context context;
    List<Meal> mealPlans;
    private OnClickPlanListener listner;

    public PlanAdapter(Context context, List<Meal> mealPlans, OnClickPlanListener listner) {
        this.context = context;
        this.mealPlans = mealPlans;
        this.listner = listner;
    }

    @NonNull
    @Override
    public PlanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_plan,parent,false);
        MyViewHolder myViewHolder = new PlanAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal product = mealPlans.get(position);
        holder.tvTitle.setText("Title of Meal :  " +mealPlans.get(position).getStrMeal());
        Glide.with(context).load(mealPlans.get(position).getStrMealThumb()).into(holder.img);
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onPlanProductClick(product, product.getDay());

            }
        });


    }

    @Override
    public int getItemCount() {
        return mealPlans.size();
    }
    public  void SetList(List<Meal>updateProducts){
        this.mealPlans=updateProducts;


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
            tvTitle = itemView.findViewById(R.id.Name_of_plan_meal);
            img=itemView.findViewById(R.id.imageViewPlan);
            Delete=itemView.findViewById(R.id.delete_plan_button);

        }
    }
}
