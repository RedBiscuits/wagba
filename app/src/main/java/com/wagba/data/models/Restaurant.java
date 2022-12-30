package com.wagba.data.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private String name = "";
    private String category = "";
    private String imageUrl = "";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public Restaurant() {
    }

    public Restaurant(Parcel in) {
        this.category = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.category);
        parcel.writeString(this.name);
        parcel.writeString(this.imageUrl);
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>(){

        @Override
        public Restaurant createFromParcel(Parcel parcel) {
            return new Restaurant(parcel);
        }

        @Override
        public Restaurant[] newArray(int i) {
            return new Restaurant[i];
        }
    };
}
