//package com.example.project_app.model;
//
//import androidx.annotation.NonNull;
//import androidx.room.PrimaryKey;
//import androidx.room.Relation;
//
//public class User {
//
//
//    @PrimaryKey
//    @NonNull
//    private String userEmail;
//    @Relation(
//            parentColumn = "userEmail",
//            entityColumn = "userEmail"
//    )
//    @NonNull
//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(@NonNull String userEmail) {
//        this.userEmail = userEmail;
//    }
//
//    public User(@NonNull String userEmail) {
//        this.userEmail = userEmail;
//    }
//}
