package com.wagba.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.wagba.R;
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.food_item, parent, false);

        return new FoodViewHolder(listItem);    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.foodName.setText(foodList.get(position).getName());
        holder.foodCategory.setText(foodList.get(position).getCategory());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(24));
        Glide.with(context).load(foodList.get(position).getImageUrl())
                .apply(requestOptions)
                .skipMemoryCache(true)
                .into(holder.foodImage);
        holder.addToCart.setOnClickListener(view -> Toast.makeText(context , "TODO" , Toast.LENGTH_SHORT).show());
        holder.foodDescription.setText(foodList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        public ShapeableImageView foodImage;
        public TextView foodName;
        public TextView foodCategory;
        public MaterialButton addToCart;
        public TextView foodDescription;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            this.foodCategory = itemView.findViewById(R.id.food_category);
            this.foodName = itemView.findViewById(R.id.food_name);
            this.addToCart = itemView.findViewById(R.id.add_to_cart_btn);
            this.foodDescription = itemView.findViewById(R.id.food_description);
            this.foodImage = itemView.findViewById(R.id.food_image);

        }
    }
}
