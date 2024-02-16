package com.example.project_app.network;


import com.example.project_app.model.areaResponse;
import com.example.project_app.model.categoryResponce;
import com.example.project_app.model.mealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("random.php")
    Observable<mealResponse> getMeals();
    @GET("categories.php")
    Observable<categoryResponce> getCategory();
    @GET("search.php")
    Observable<mealResponse> getSearch(@Query("s") String query);
    @GET("filter.php")
    Observable<mealResponse>  getSearchByCategory(@Query("c") String category);
    @GET("filter.php")
    Observable<mealResponse>  getSearchByIngredient(@Query("i") String ingredient);
    @GET("list.php?a=list")
    Observable<areaResponse> getAreas();
    @GET("filter.php")
    Observable<mealResponse> getSearchByArea(@Query("a") String area);

}
