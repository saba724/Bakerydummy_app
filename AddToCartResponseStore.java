package com.example.bakeryapp.data;

import com.google.gson.annotations.SerializedName;

public class AddToCartResponseStore {
    @SerializedName("error")
    public String error;
    @SerializedName("message")
    public String message;
}
