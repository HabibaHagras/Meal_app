package com.example.project_app.favMeals.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.project_app.R;
import com.example.project_app.dp.AppDataBase;
import com.example.project_app.dp.MealDAO;
import com.example.project_app.dp.MealLocalDataSourceIm;
import com.example.project_app.favMeals.presenter.FavPresenter;
import com.example.project_app.favMeals.presenter.FavPresenterIm;
import com.example.project_app.model.Meal;
import com.example.project_app.model.Repository;
import com.example.project_app.model.mealRepositoryIm;
import com.example.project_app.network.MealClient;
import com.example.project_app.network.MealRemoteDataSourceIm;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity implements OnClickFavListener, AllFavView {
    RecyclerView favRecyclerView;
    LinearLayoutManager linearLayoutManager;
    MealClient mealClient;
    FavAdapter favproductAdapter;
    AppDataBase dp;
    MealDAO DAO;
    Repository repo ;
    FavPresenter favPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        favRecyclerView=findViewById(R.id.rv_fav);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        favRecyclerView.setLayoutManager(linearLayoutManager);
        mealClient=MealClient.getInstance();
        favproductAdapter = new FavAdapter(this, new ArrayList<>(),this);
        favRecyclerView.setAdapter(favproductAdapter);
        favRecyclerView.setLayoutManager(linearLayoutManager);
        dp=AppDataBase.getInstance(this);
        DAO=dp.getmealDAO();
        repo=Repository.getInstance(this);
        // favproductAdapter.SetList( repo.getStoredProduct());
        favproductAdapter.notifyDataSetChanged();
        favPresenter=new FavPresenterIm(this, mealRepositoryIm.getInstance(MealRemoteDataSourceIm.getInstance(),
                MealLocalDataSourceIm.getInstance(this)));
        LiveData<List<Meal>> productList=DAO.getAllMeals();
        productList.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                favproductAdapter.SetList(meals);
                favproductAdapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void showdata(List<Meal> products) {
    favPresenter.getMeal();
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void deleteProduct(Meal meal) {
        favPresenter.deleteproduct(meal);


    }

    @Override
    public void onFavProductClick(Meal meal) {
        Toast.makeText(FavActivity.this,"DELETED",Toast.LENGTH_SHORT).show();
        deleteProduct(meal);
    }
}