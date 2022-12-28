package com.wagba.ui.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wagba.data.models.Restaurant;
import com.wagba.data.repositories.DataRepository;

import java.util.ArrayList;
import java.util.List;

public class DataViewModel extends ViewModel {
    private MutableLiveData<List<Restaurant>> _restaurants = new MutableLiveData();
    public LiveData<List<Restaurant>> getRestaurants(){
        return _restaurants;
    }

    private MutableLiveData<Boolean> _error = new MutableLiveData();
    public LiveData<Boolean> getError(){
        return _error;
    }

    public void fetchRestaurantsData(){
        _error.postValue(false);
        DataRepository.getRestaurantResponse().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    List<Restaurant> temp = new ArrayList<>();
                    for (QueryDocumentSnapshot restaurant : task.getResult()){
                        temp.add(restaurant.toObject(Restaurant.class));
                    }
                    _restaurants.postValue(temp);
                }else{
                    _error.postValue(true);
                }
            }
        });
    }
}
