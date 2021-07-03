package com.example.bakeryapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddToCartResponseFetching {
    @SerializedName("cart_details")
    @Expose
    public List<AddToCartResponseFetching.CartDEtalis> cartDEtalisList;
    public class CartDEtalis{
        @SerializedName("p_id")
        @Expose
        public String p_id1;

        @SerializedName("p_name")
        @Expose
        public String p_name1;

        @SerializedName("p_prize")
        @Expose
        public String p_prize1;

        @SerializedName("url")
        @Expose
        public String url1;

        @SerializedName("card_id")
        @Expose
        public String card_id1;


        @SerializedName("t_prize")
        @Expose
        public String t_prize1;








    }




}
