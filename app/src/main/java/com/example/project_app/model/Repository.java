//package com.example.project_app.model;
//
//import android.content.Context;
//
//import androidx.lifecycle.LiveData;
//
//import com.example.project_app.dp.AppDataBase;
//import com.example.project_app.dp.MealDAO;
//
//import java.util.List;
//
//public class Repository {
//    private Context context;
//    private MealDAO productDAO;
//    private LiveData<List<Meal>> storedMeal;
//    private static Repository repo =null;
//    AppDataBase dp;
//    MealDAO DAO;
//    private Repository(Context context){
//        this.context=context;
//        dp= AppDataBase.getInstance(context.getApplicationContext());
//        DAO=dp.getmealDAO();
//        storedMeal=DAO.getAllMeals();
//    }
//public static Repository getInstance(Context context){
//        if (repo==null){
//            repo =new Repository(context);
//        }
//        return repo;
//}
//public  LiveData<List<Meal>> getStoredMeal(){
//        return storedMeal;
//}
//public  void delete(Meal meal){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DAO.delete(meal);
//            }
//        });
//
//}
//
//}
