package com.example.bakeryapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderResponseFetching {
    @SerializedName("Orders_details")
    @Expose
    public List<OrderResponseFetching.OrderDetails> orderDetailsList;


    public class OrderDetails {
        @SerializedName("p_name")
        @Expose
        public String p_name1;

        @SerializedName("p_prize")
        @Expose
        public String p_prize1;

        @SerializedName("url")
        @Expose
        public String url;






    }
}
