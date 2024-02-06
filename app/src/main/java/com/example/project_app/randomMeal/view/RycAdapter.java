package com.example.project_app.randomMeal.view;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_app.R;
import com.example.project_app.model.Meal;

import java.util.ArrayList;
import java.util.List;

public  class RycAdapter extends RecyclerView.Adapter<RycAdapter.MyViewHolder> {


    Context context;
    List<Meal> products;
    private  PutInFavListener listner;

    public RycAdapter(Context context, List<Meal> products ,PutInFavListener _listener){
        this.context = context;
        this.products = products;
        this.listner=_listener;
        products=new ArrayList<>();

    }
    public  void SetList(List<Meal>updateProducts){
        this.products=updateProducts;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_meal,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal product = products.get(position);
//        holder.tvTitle.setText("Title:  " +products.get(position).getTitle());
//        holder.tvPrice.setText("Price:  " + products.get(position).getProductPrice () );
//        holder.tvBrand.setText("Brand:  " +products.get(position).getProductBrand());
//        holder.tvDesc.setText("Desc:  " +products.get(position).getProductcategory());
//        holder.ratingBar.setText("Rating:  " +products.get(position).getRating().toString());
//        Glide.with(context).load(products.get(position).getProductthumbnail()).into(holder.img);
        holder.tvTitle.setText("Title of Meal :  " +products.get(position).getStrMeal());

        Glide.with(context).load(products.get(position).getStrMealThumb()).into(holder.img);




//        class Downloader extends AsyncTask<String,Void, Bitmap> {
//
//            @Override
//            protected Bitmap doInBackground(String... url) {
//                return downloadImage(url[0]);
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                super.onPostExecute(bitmap);
//                holder.img.setImageBitmap(bitmap);
//            }
//        }
//        Downloader downloader =new Downloader();
//        downloader.execute(products.get(position).getProductthumbnail());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvPrice;
        TextView tvBrand;
        TextView tvDesc;
        ImageView img;
        TextView ratingBar;
        Button Fav;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.Name_of_meal);
            img=itemView.findViewById(R.id.imageView);




        }
    }}