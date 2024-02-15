package com.example.project_app.network;


import com.example.project_app.model.areaResponse;
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

public class MealRemoteDataSourceIm implements MealRemoteDataSource {
    private List<Meal> allMeals;
    public static MealRemoteDataSourceIm client =null;
    MealService myService;
    private MealRemoteDataSourceIm() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create(gson)).build();
         myService = retrofit.create(MealService.class);

    }
    public static MealRemoteDataSourceIm getInstance(){
        if (client==null){
            client = new MealRemoteDataSourceIm();

        }
        return client;
    }
@Override
    public void makeNetwokCall(NetworkCallback networkCallback) {


        Call<mealResponse> call = myService.getMeals();
        call.enqueue(new Callback<mealResponse>(){


            @Override
            public void onResponse(Call<mealResponse> call, Response<mealResponse> response) {
                networkCallback.onSucessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<mealResponse> call, Throwable t) {
                networkCallback.onFailuer(t.getMessage());

            }
        });
    }

    @Override
    public void makeNetwokCallCategory(NetworkCallback networkCallback) {
        Call<categoryResponce> call2 = myService.getCategory();
        call2.enqueue(new Callback<categoryResponce>(){
            @Override
            public void onResponse(Call<categoryResponce> call, Response<categoryResponce> response) {
                networkCallback.onSucessResultCategory(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<categoryResponce> call, Throwable t) {
                networkCallback.onFailuer(t.getMessage());

            }
        });
}

    @Override
    public void makeNetwokCallSearch(NetworkCallback networkCallback, String wordMeal) {
            Call<mealResponse> call3 = myService.getSearch(wordMeal);
            call3.enqueue(new Callback<mealResponse>(){
                @Override
                public void onResponse(Call<mealResponse> call, Response<mealResponse> response) {
                    networkCallback.onSucessResult(response.body().getMeals());
                }

                @Override
                public void onFailure(Call<mealResponse> call, Throwable t) {
                    networkCallback.onFailuer(t.getMessage());

                }
            });

    }
    @Override
    public void makeNetwokCallSearchCategory(NetworkCallback networkCallback, String wordCategory) {
        Call<mealResponse> call4 = myService.getSearchByCategory(wordCategory);
        call4.enqueue(new Callback<mealResponse>(){
            @Override
            public void onResponse(Call<mealResponse> call, Response<mealResponse> response) {
                networkCallback.onSucessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<mealResponse> call, Throwable t) {
                networkCallback.onFailuer(t.getMessage());

            }
        });

    }

    @Override
    public void makeNetwokCallSearchIngredient(NetworkCallback networkCallback, String ingredient) {
        Call<mealResponse> call5 = myService.getSearchByIngredient(ingredient);
        call5.enqueue(new Callback<mealResponse>(){
            @Override
            public void onResponse(Call<mealResponse> call, Response<mealResponse> response) {
                networkCallback.onSucessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<mealResponse> call, Throwable t) {
                networkCallback.onFailuer(t.getMessage());

            }
        });
    }

    @Override
    public void makeNetwokCallSearchArea(NetworkCallback networkCallback, String area) {
        Call<mealResponse> call6 = myService.getSearchByArea(area);
        call6.enqueue(new Callback<mealResponse>(){
            @Override
            public void onResponse(Call<mealResponse> call, Response<mealResponse> response) {
                networkCallback.onSucessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<mealResponse> call, Throwable t) {
                networkCallback.onFailuer(t.getMessage());

            }
        });
    }

    @Override
    public void makeNetwokCallArea(NetworkCallback networkCallback) {
        Call<areaResponse> call5 = myService.getAreas();
        call5.enqueue(new Callback<areaResponse>(){
            @Override
            public void onResponse(Call<areaResponse> call, Response<areaResponse> response) {
                networkCallback.onSucessResultArea(response.body().getAreas());
            }

            @Override
            public void onFailure(Call<areaResponse> call, Throwable t) {
                networkCallback.onFailuerArea(t.getMessage());

            }
        });
    }

}
