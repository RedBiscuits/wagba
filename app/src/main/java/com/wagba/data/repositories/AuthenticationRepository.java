package com.wagba.data.repositories;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.wagba.data.firebase.FirebaseHelper;

public class AuthenticationRepository {
    public static Task<AuthResult> loginUser(String email , String password){
        return FirebaseHelper.loginUser(email , password);
    }

    public static Task<SignInMethodQueryResult> checkEmail(String email){
        return FirebaseHelper.checkEmailAvailability(email);
    }

}
