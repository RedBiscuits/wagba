package com.wagba.ui.helpers;

import com.wagba.data.models.CartItem;
import com.wagba.data.models.Food;

import java.util.ArrayList;

public class CartContentHelper {
    private static ArrayList<CartItem> content = new ArrayList();

    public static void addItem(Food food) {
        for (CartItem item1 : content) {
            if (item1.getImageUrl().equals(food.getImageUrl()))
                return;
        }
        content.add(new CartItem(food));
    }


    public static ArrayList<CartItem> getContent() {
        return content;
    }

    public static Long getTotal(){
        Long total = 0L;
        for(CartItem item : content){
            total += item.getTotalPrice();
        }
        return total;
    }
}

