package com.wagba.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.wagba.R;
import com.wagba.data.models.Restaurant;
import com.wagba.ui.helpers.NavigationHelper;
import com.wagba.ui.home.HomeFragmentDirections;
import com.wagba.ui.home.food.FoodFragment;
import com.wagba.ui.home.food.FoodFragmentArgs;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder> {

    List<Restaurant> restaurants = new ArrayList<>();
    Context context;


    public RestaurantsAdapter(Context context) {
        this.context = context;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.restaurant_tem, parent, false);

        return new RestaurantsViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        holder.restaurantName.setText(restaurants.get(position).getName());
        holder.restaurantCategory.setText(restaurants.get(position).getCategory());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(24));
        Glide.with(context).load(restaurants.get(position).getImageUrl())
                .apply(requestOptions)
                .skipMemoryCache(true)
                .into(holder.restaurantImage);

        HomeFragmentDirections.ActionSecondFragmentToFoodFragment
                action = HomeFragmentDirections.actionSecondFragmentToFoodFragment(restaurants.get(position));

        holder.restaurantLayout.setOnClickListener(view -> {
            NavigationHelper.navigate(action);
        }
        );

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class RestaurantsViewHolder extends RecyclerView.ViewHolder {
        public ShapeableImageView restaurantImage;
        public TextView restaurantName;
        public TextView restaurantCategory;
        public ConstraintLayout restaurantLayout;

        public RestaurantsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.restaurantCategory = itemView.findViewById(R.id.restaurant_category);
            this.restaurantName = itemView.findViewById(R.id.restaurant_name);
            this.restaurantImage = itemView.findViewById(R.id.restaurant_image);
            this.restaurantLayout= itemView.findViewById(R.id.restaurant_item);
        }
    }


}
