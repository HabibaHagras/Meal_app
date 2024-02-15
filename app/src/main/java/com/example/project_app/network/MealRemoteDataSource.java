package com.example.project_app.network;

public interface MealRemoteDataSource {
    void makeNetwokCall(NetworkCallback networkCallback);
    void makeNetwokCallCategory(NetworkCallback networkCallback);
    void makeNetwokCallSearch(NetworkCallback networkCallback,String wordMeal);
    void makeNetwokCallSearchCategory(NetworkCallback networkCallback,String wordCategory);
    void makeNetwokCallSearchIngredient(NetworkCallback networkCallback,String ingredient);
    void makeNetwokCallArea(NetworkCallback networkCallback);

}
