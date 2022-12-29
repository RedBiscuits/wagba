package com.wagba.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

@Entity
public class User {
    private String email = "", username = "";
    @PrimaryKey
    @NonNull
    private String userID = "";
    private String userToken = "";
    private String phoneNumber = "";

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public User(String email, String username, String userID, String userToken, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.userID = userID;
        this.userToken = userToken;
        this.phoneNumber = phoneNumber;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getEmail() {
        return email;
    }


    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }
}
