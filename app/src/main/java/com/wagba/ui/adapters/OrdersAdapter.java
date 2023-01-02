package com.wagba.ui.adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.wagba.data.models.Order;
import com.wagba.data.models.Restaurant;
import com.wagba.ui.helpers.NavigationHelper;
import com.wagba.ui.home.HomeFragmentDirections;
import com.wagba.ui.home.food.FoodFragment;
import com.wagba.ui.home.food.FoodFragmentArgs;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    List<Order> orders = new ArrayList<>();

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.porder_item, parent, false);

        return new OrdersViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        holder.orderTime.setText(orders.get(position).getTime());

        holder.orderConfirmed.setText(orders.get(position).getStatus());
        holder.orderConfirmed.setTextColor(Color.parseColor("#00FF00"));

        holder.orderItems.setText(orders.get(position).getItems());
        holder.orderEmail.setText(orders.get(position).getEmail());
        holder.orderAmount.setText(orders.get(position).getAmount());
        holder.orderDate.setText(orders.get(position).getDate());
        holder.orderLocation.setText(orders.get(position).getLocation());


    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {
        public TextView orderAmount;
        public TextView orderLocation;
        public TextView orderConfirmed;
        public TextView orderItems;
        public TextView orderEmail;
        public TextView orderTime;
        public TextView orderDate;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            this.orderAmount= itemView.findViewById(R.id.porder_amount_TV);
            this.orderConfirmed= itemView.findViewById(R.id.porder_confirmed_TV);
            this.orderEmail = itemView.findViewById(R.id.porder_email_TV);
            this.orderLocation= itemView.findViewById(R.id.porder_location_TV);
            this.orderItems= itemView.findViewById(R.id.porder_items_TV);
            this.orderTime= itemView.findViewById(R.id.porder_time_TV);
            this.orderDate= itemView.findViewById(R.id.porder_date_TV);

        }
    }


}
