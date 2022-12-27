package com.wagba.data.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseHelper {
    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

    public static Boolean loginUser(String email , String password){
       return firebaseAuth.signInWithEmailAndPassword(email , password).isSuccessful();
    }

    public static Boolean registerUser(String email , String password){
         return firebaseAuth.createUserWithEmailAndPassword(email  , password).isSuccessful();
    }

}
