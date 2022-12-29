package com.wagba.data.models;

import com.google.firebase.firestore.PropertyName;

public class Food {
    @PropertyName("name")
    public String name = "";

    @PropertyName("category")
    public String category = "";

    @PropertyName("imageUrl")
    public String imageUrl = "";

    @PropertyName("price")
    public Long price = 0L;

    @PropertyName("description")
    public String description ="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
