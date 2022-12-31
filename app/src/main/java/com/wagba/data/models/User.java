package com.wagba.data.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.Objects;

@Entity
public class User {
    @Nullable
    private String email, username;
    @PrimaryKey
    @NonNull
    private String userID;
    private String userToken;
    private String phoneNumber;
    private double totalWalletAmount = 0.0;
    private double consumedWalletAmount = 0.0;
    private double remainingWalletAmount = 0.0;
    public User(String email, String username, String userID, String userToken, String phoneNumber) {
        this.email = email;
       this.username = username;
        this.userID = userID;
        this.userToken = userToken;
        this.phoneNumber =  phoneNumber;

    }    public double getTotalWalletAmount() {
        return totalWalletAmount;
    }

    public void setTotalWalletAmount(double totalWalletAmount) {
        this.totalWalletAmount = totalWalletAmount;
    }

    public double getConsumedWalletAmount() {
        return consumedWalletAmount;
    }

    public void setConsumedWalletAmount(double consumedWalletAmount) {
        this.consumedWalletAmount = consumedWalletAmount;
    }

    public double getRemainingWalletAmount() {
        return remainingWalletAmount;
    }

    public void setRemainingWalletAmount(double remainingWalletAmount) {
        this.remainingWalletAmount = (remainingWalletAmount);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserID(@NonNull String userID) {
        this.userID = userID;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public void setPhoneNumber(String phoneNumber) {
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
