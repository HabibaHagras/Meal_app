package com.example.project_app.randomMeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_app.R;
import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>  {
    Context context;
    List<Category> products;
    private  PutInFavListener listner;

    public CategoryAdapter(Context context, List<Category> products, PutInFavListener listner) {
        this.context = context;
        this.products = products;
        this.listner = listner;
        products=new ArrayList<>();


    }
    public  void SetList(List<Category>updateProducts){
        this.products=updateProducts;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_category,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category product = products.get(position);
//        holder.tvTitle.setText("Title:  " +products.get(position).getTitle());
//        holder.tvPrice.setText("Price:  " + products.get(position).getProductPrice () );
//        holder.tvBrand.setText("Brand:  " +products.get(position).getProductBrand());
//        holder.tvDesc.setText("Desc:  " +products.get(position).getProductcategory());
//        holder.ratingBar.setText("Rating:  " +products.get(position).getRating().toString());
//        Glide.with(context).load(products.get(position).getProductthumbnail()).into(holder.img);
        holder.tvCategory.setText("category of Meal :  " +products.get(position).getStrCategory());

        Glide.with(context).load(products.get(position).getStrCategoryThumb()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvCategory;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_Category);
            img=itemView.findViewById(R.id.imageView_Category);
        }
    }

    }
