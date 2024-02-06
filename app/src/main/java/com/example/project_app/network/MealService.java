package com.example.project_app.network;


import com.example.project_app.model.categoryResponce;
import com.example.project_app.model.mealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("random.php")
    Call<mealResponse> getMeals();
    @GET("categories.php")
    Call<categoryResponce> getCategory();
}
