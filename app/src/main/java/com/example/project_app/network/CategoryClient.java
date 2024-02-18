package com.example.project_app.network;

import com.example.project_app.model.Category;
import com.example.project_app.model.Meal;
import com.example.project_app.model.mealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryClient {
    private List<Category> allCategories;
    public static CategoryClient client =null;

    public CategoryClient() {

    }
    public static CategoryClient getInstance(){
        if (client==null){
            client = new CategoryClient();

        }
        return client;
    }

    public void makeNetwokCall(NetworkCallback networkCallback) {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        MealService myService = retrofit.create(MealService.class);
        //Call<mealResponse> call = myService.getMeals();
//        call.enqueue(new Callback<mealResponse>(){
//
//
//            @Override
//            public void onResponse(Call<mealResponse> call, Response<mealResponse> response) {
//                networkCallback.onSucessResult(response.body().getMeals());
//            }
//
//            @Override
//            public void onFailure(Call<mealResponse> call, Throwable t) {
//                networkCallback.onFailuer(t.getMessage());
//
//            }
//        });
    }
}
