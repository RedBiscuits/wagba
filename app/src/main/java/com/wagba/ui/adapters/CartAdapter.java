package com.wagba.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.wagba.R;
import com.wagba.data.models.CartItem;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<CartItem> cartItems = new ArrayList<>();

    Context context;

    public void setCartItems(ArrayList<CartItem> foodList) {
        this.cartItems = foodList;
    }

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cart_item, parent, false);

        return new CartAdapter.CartViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cartItemName.setText(cartItems.get(position).getName());
        holder.cartItemDesc.setText(cartItems.get(position).getDescription());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(24));
        Glide.with(context).load(cartItems.get(position).getImageUrl())
                .apply(requestOptions)
                .skipMemoryCache(true)
                .into(holder.cartImage);
        holder.increaseCount.setOnClickListener(view -> {
            Long count = Long.parseLong(holder.itemCount.getText().toString());
            count++;
            holder.itemCount.setText(count.toString());
            Long total = count * cartItems.get(position).getPrice();
            holder.cartItemPrice.setText(total.toString());
        });

        holder.decreaseCount.setOnClickListener(view -> {
            Long count = Long.parseLong(holder.itemCount.getText().toString());
            if (count > 0) {
                count--;
                holder.itemCount.setText(count.toString());
                Long total = count * cartItems.get(position).getPrice();
                holder.cartItemPrice.setText(total.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    protected class CartViewHolder extends RecyclerView.ViewHolder {

        public ShapeableImageView cartImage;
        public TextView cartItemName;
        public TextView cartItemDesc;
        public FloatingActionButton increaseCount;
        public FloatingActionButton decreaseCount;
        public TextView cartItemPrice;
        public EditText itemCount;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cartItemPrice = itemView.findViewById(R.id.cart_item_price);
            this.cartItemDesc = itemView.findViewById(R.id.cart_description);
            this.cartItemName = itemView.findViewById(R.id.cart_name);
            this.cartImage = itemView.findViewById(R.id.cart_image);
            this.increaseCount = itemView.findViewById(R.id.increase_count);
            this.decreaseCount = itemView.findViewById(R.id.deacrease_items_count);
            this.itemCount = itemView.findViewById(R.id.cart_items_count);
        }
    }
}
