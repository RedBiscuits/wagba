package com.wagba.data.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class FirebaseHelper {
    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

    public static void logoutUser(){
        firebaseAuth.signOut();
    }

    public static Task<AuthResult> loginUser(String email , String password){

       return firebaseAuth.signInWithEmailAndPassword(email , password);
    }

    public static Boolean registerUser(String email , String password){
         return firebaseAuth.createUserWithEmailAndPassword(email  , password).isSuccessful();
    }

    public static Task<SignInMethodQueryResult> checkEmailAvailability(String email){
         return firebaseAuth.fetchSignInMethodsForEmail(email);
    }

}
