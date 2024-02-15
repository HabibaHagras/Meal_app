package com.example.project_app.randomMeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_app.R;
import com.example.project_app.model.Area;
import com.example.project_app.model.Category;

import java.util.List;

public class AreaAdapter extends  RecyclerView.Adapter<AreaAdapter.MyViewHolder> {
    Context context;
    List<Area> areas;
    private  PutInFavListener listner;

    public AreaAdapter(Context context, List<Area> areas, PutInFavListener listner) {
        this.context = context;
        this.areas = areas;
        this.listner = listner;
    }
    public  void SetList(List<Area>updateProducts){
        this.areas=updateProducts;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_countries,parent,false);
        AreaAdapter.MyViewHolder myViewHolder = new AreaAdapter.MyViewHolder(view);
        return myViewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.MyViewHolder holder, int position) {
        Area area = areas.get(position);
        holder.tvCountry.setText("category of Meal :  " +areas.get(position).getStrArea());
    }

    @Override
    public int getItemCount() {
        return areas != null ? areas.size() : 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvCountry;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.Name_ofCountry);
        }
    }

}