package com.wagba.data.firebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.function.DoubleBinaryOperator;

public class FirebaseHelper {
    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String restaurantCollection = "restaurants";
    private static final String foodCollection = "food";
    public static FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

    public static void logoutUser(){
        firebaseAuth.signOut();
    }

    public static Task<AuthResult> loginUser(String email , String password){
       return firebaseAuth.signInWithEmailAndPassword(email , password);
    }

    public static Task<AuthResult> registerUser(String email , String password){
         return firebaseAuth.createUserWithEmailAndPassword(email  , password);
    }
    public static Task<SignInMethodQueryResult> checkEmailAvailability(String email){

        return firebaseAuth.fetchSignInMethodsForEmail(email);
    }


    public static void updatePhoneNum(PhoneAuthCredential credentials){
        firebaseAuth.getCurrentUser().updatePhoneNumber(credentials);
    }
    public static Task<QuerySnapshot> getRestaurants(){
        return db.collection(restaurantCollection).get();
    }
    public static Task<QuerySnapshot> getFoodByCategory(String category){
        return db.collection(foodCollection).whereEqualTo("category" , category).get();
    }
}
