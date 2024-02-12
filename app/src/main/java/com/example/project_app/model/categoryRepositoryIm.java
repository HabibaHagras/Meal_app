//package com.example.project_app.model;
//
//import com.example.project_app.dp.MealLocalDataSource;
//import com.example.project_app.network.MealRemoteDataSource;
//import com.example.project_app.network.NetworkCallback;
//
//public class categoryRepositoryIm implements categoryRepository{
//    MealRemoteDataSource remoteDataSource;
//
//    public categoryRepositoryIm(MealRemoteDataSource remoteDataSource) {
//        this.remoteDataSource = remoteDataSource;
//    }
//    public static categoryRepositoryIm getInstance(MealRemoteDataSource MealRemoteDataSource ){
//        if (repo==null){
//            repo=new categoryRepositoryIm(MealRemoteDataSource);
//        }
//        return  repo;
//    }
//
//    private  static categoryRepositoryIm repo =null;
//    @Override
//    public void getAllCategories(NetworkCallback networkCallback) {
//        remoteDataSource.makeNetwokCall(networkCallback);
//
//    }
//}
