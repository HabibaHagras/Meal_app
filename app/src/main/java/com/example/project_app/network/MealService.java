package com.example.project_app.network;


import com.example.project_app.model.categoryResponce;
import com.example.project_app.model.mealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("random.php")
    Call<mealResponse> getMeals();
    @GET("categories.php")
    Call<categoryResponce> getCategory();
    @GET("search.php")
    Call<mealResponse> getSearch(@Query("s") String query);

}
