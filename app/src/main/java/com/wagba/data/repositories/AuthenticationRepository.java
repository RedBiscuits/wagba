package com.wagba.data.repositories;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.wagba.data.db.UserDb;
import com.wagba.data.firebase.FirebaseHelper;
import com.wagba.data.models.User;

public class AuthenticationRepository {
    public static Task<AuthResult> loginUser(String email , String password){
        return FirebaseHelper.loginUser(email , password);
    }

    public static Task<AuthResult> registerUser(String email , String password){
        return FirebaseHelper.registerUser(email , password);
    }

    public static Task<SignInMethodQueryResult> checkEmail(String email){
        return FirebaseHelper.checkEmailAvailability(email);
    }

    public static FirebaseUser getCurrentUser(){
        return FirebaseHelper.getCurrentUser();
    }

    public static void saveUser(Context context){
        FirebaseUser user = getCurrentUser();
        UserDb.getDatabase(context).userDao().addUser(new User(user.getEmail()
                , user.getDisplayName()
                , user.getUid()
                , user.getTenantId()
                , user.getPhoneNumber()));
    }

}
