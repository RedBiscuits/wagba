package com.wagba.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wagba.data.models.Food;
import com.wagba.data.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    List<Food> foodList = new ArrayList<>();
    Context context;

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public FoodAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
