package com.example.bakeryapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListRep {

//    @SerializedName("error")
//    @Expose
//    public String error;
//
//    @SerializedName("message")
//    @Expose
//    public String message;

    @SerializedName("CreamCake_product")
    @Expose
    public List<ProductListRep.ProductList> productlist;

    public class ProductList{

        @SerializedName("p_id")
        @Expose
        public String product_id;

        @SerializedName("p_name")
        @Expose
        public String p_name1;

        @SerializedName("p_prize")
        @Expose
        public String product_prize;

        @SerializedName("url")
        @Expose
        public String image_url;

//        @SerializedName("error")
//        @Expose
//        public String error;
//        @SerializedName("p_prize")
//        public  String product_prize;
//        @SerializedName("url")
//        public int  image_url;
//
//        public String getProduct_name() {
//            return product_name;
//        }
//
//        public void setProduct_name(String product_name) {
//            this.product_name = product_name;
//        }
//
//        public String getProduct_prize() {
//            return product_prize;
//        }
//
//        public void setProduct_prize(String product_prize) {
//            this.product_prize = product_prize;
//        }
//
//        public int getImage_url() {
//            return image_url;
//        }
//
//        public void setImage_url(int image_url) {
//            this.image_url = image_url;
//        }
    }
}
