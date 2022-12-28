package com.wagba.data.repositories;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.QuerySnapshot;
import com.wagba.data.firebase.FirebaseHelper;

public class DataRepository {
    public static Task<QuerySnapshot> getRestaurantResponse(){
        return FirebaseHelper.getRestaurants();
    }
}
