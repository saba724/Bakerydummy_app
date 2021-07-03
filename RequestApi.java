package com.example.bakeryapp.api;

import com.example.bakeryapp.data.AddToCartResponseFetching;
import com.example.bakeryapp.data.AddToCartResponseStore;
import com.example.bakeryapp.data.GrandTotalFetchingResponse;
import com.example.bakeryapp.data.LoginRespone;
import com.example.bakeryapp.data.OrderResponseFetching;
import com.example.bakeryapp.data.OrderResponseStoring;
import com.example.bakeryapp.data.ProductListRep;
import com.example.bakeryapp.data.ProductRemoveResponse;
import com.example.bakeryapp.data.ProfileResponse;
import com.example.bakeryapp.data.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestApi {
    @POST("bk_register.php")
    Call<RegisterResponse> register(@Query("name") String name,
                                    @Query("mobile") String mobile,
                                    @Query("city") String city,
                                    @Query("email") String email,
                                    @Query("password")String password,
                                    @Query("address") String address,
                                    @Query("dob") String dob);
    @GET("bk_login.php")
    Call<LoginRespone> login(@Query("email") String email,
                              @Query("password") String password);

    @GET("fetchi_pdetails.php")
    Call<ProductListRep> get_product(@Query("cat_id") String cat_id);



    @GET("profilefetching.php")
    Call<ProfileResponse> get_profile(@Query("email") String email);

    @GET("addcartstore.php")//emailid and image id storing into addcart table
    Call<AddToCartResponseStore> addToCartStore(@Query("email") String email,
                                             @Query("product_id") String product_id);


    @GET("cartfetching.php")//fetching the data from add to crt table and displaying to cart fragment
    Call<AddToCartResponseFetching> addToCartFetching(@Query("email") String email);

    @GET("productremove.php")//removing the product from cart table
    Call<ProductRemoveResponse> productRemove(@Query("product_id") String product_id,
                                                @Query("cart_status") String cart_status,
                                              @Query("u_id") String u_id);


    @GET("grandprizefetching.php")//fetching the grand total prize
    Call<GrandTotalFetchingResponse> grandTotal(@Query("email") String email);


    @GET("orderstoring.php")//ordering api for storing
    Call<OrderResponseStoring> orderStoring(@Query("email") String email);


    @GET("orderfetching.php")//api for order fetching to display in order fragment
    Call<OrderResponseFetching> orderFetching();




}




