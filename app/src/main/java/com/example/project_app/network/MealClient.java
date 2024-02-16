package com.example.project_app.network;


import com.example.project_app.model.Meal;
import com.example.project_app.model.categoryResponce;
import com.example.project_app.model.mealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient {
//    private final Context context;
    private List<Meal> allMeals;
    public static MealClient client =null;

    public MealClient() {

    }
    public static MealClient getInstance(){
        if (client==null){
            client = new MealClient();

        }
        return client;
    }

    public void makeNetwokCall(NetworkCallback networkCallback) {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        MealService myService = retrofit.create(MealService.class);
//        Call<mealResponse> call = myService.getMeals();
//        Call<categoryResponce> call2 =myService.getCategory();
//        call.enqueue(new Callback<mealResponse>(){
//
//
//            @Override
//            public void onResponse(Call<mealResponse> call, Response<mealResponse>response) {
//                networkCallback.onSucessResult(response.body().getMeals());
//                }
//
//            @Override
//            public void onFailure(Call<mealResponse> call, Throwable t) {
//                networkCallback.onFailuer(t.getMessage());
//
//            }
//        });

//        call2.enqueue(new Callback<categoryResponce>() {
//            @Override
//            public void onResponse(Call<categoryResponce> call, Response<categoryResponce> response) {
//                networkCallback.onSucessResultCategory(response.body().getCategories());
//
//            }
//
//            @Override
//            public void onFailure(Call<categoryResponce> call, Throwable t) {
//                networkCallback.onFailuerCategory(t.toString());
//
//            }
//
//
//        });
   }
}