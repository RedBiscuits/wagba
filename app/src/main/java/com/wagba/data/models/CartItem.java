package com.wagba.data.models;

public class CartItem {
    private String name = "";
    private String imageUrl = "";
    private Long price = 0L;

    public CartItem(Food food) {
        this.name = food.name;
        this.imageUrl = food.imageUrl;
        this.price = food.price;
        this.description = food.description;
        this.totalPrice = food.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    private String description ="";
    private Long totalPrice = 0L;
}
