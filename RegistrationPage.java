package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.bakeryapp.api.RequestApi;
import com.example.bakeryapp.api.RetrofitClass;
import com.example.bakeryapp.data.RegisterResponse;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPage extends AppCompatActivity {
    Button submit;
    EditText fullname,mobile,city,email,password,address,dob;
    ProgressBar load_pb;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        relativeLayout=findViewById(R.id.relative);
        submit=(Button)findViewById(R.id.submit);
        fullname=findViewById(R.id.edit_name);

        mobile=findViewById(R.id.edit_pho_no);
        city=findViewById(R.id.edit_city);
        email=findViewById(R.id.edit_email);
        password=findViewById(R.id.edit_password);
        address=findViewById(R.id.edit_address);
        dob=findViewById(R.id.edit_dob);

        load_pb = findViewById(R.id.loader);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullname.length()==0){
                    fullname.setError("Enter name");
                    fullname.requestFocus();
                    return;
                }

                if(mobile.length()==0){
                    mobile.setError("Enter Mobile Number");
                    mobile.requestFocus();
                    return;
                }
                if(city.length()==0){
                    city.setError("Enter City");
                    city.requestFocus();
                    return;
                }
                if(email.length()==0){
                    email.setError("Enter Email");
                    email.requestFocus();
                    return;
                }
                if(password.length()==0){
                    password.setError("Enter Password");
                    password.requestFocus();
                    return;
                }
                if(address.length()==0){
                    address.setError("Confirm Password");
                    address.requestFocus();
                    return;
                }
                if(dob.length()==0){
                    dob.setError("Confirm Password");
                    dob.requestFocus();
                    return;
                }
                registerProcessApi();

            }
        });




    }
    private void registerProcessApi(){
        load_pb.setVisibility(View.VISIBLE);
//        Toast.makeText(getApplicationContext(),"hi saba here",Toast.LENGTH_LONG).show();
//
//        Toast.makeText(getApplicationContext(), fullname.getText().toString().trim()+ "-" + mobile.getText().toString().trim()+ "-" +city.getText().toString().trim()+ "-" +
//                email.getText().toString().trim()+ "-" +password.getText().toString().trim(), Toast.LENGTH_LONG).show();

        RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
        Call<RegisterResponse> call=requestApi.register(fullname.getText().toString().trim(), mobile.getText().toString().trim(),city.getText().toString().trim(),
                email.getText().toString().trim(),password.getText().toString().trim(),address.getText().toString(),dob.getText().toString());
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                load_pb.setVisibility(View.GONE);
               // Toast.makeText(getApplicationContext(), "response", Toast.LENGTH_LONG).show();
                if(response.body().error.equalsIgnoreCase("0")){
                    Snackbar.make(relativeLayout,response.body().message,Snackbar.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationPage.this,LoginPage.class);
                    startActivity(intent);
                   // Toast.makeText(getApplicationContext(),response.body().message,Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),response.body().message,Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                load_pb.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
//            @Override
//            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
//                //Toast.makeText(RegistrationPage.this,"hi saba here",Toast.LENGTH_LONG).show();
//                load_pb.setVisibility(View.GONE);
//                if (response.isSuccessful()) {
//                       //Toast.makeText(RegistrationPage.this,"hi saba here",Toast.LENGTH_LONG).show();
//
//                    if (response.body().error.equalsIgnoreCase("1")) {
//                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
//                       // Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
//                       // startActivity(intent);
//                    } else {
//                        Toast.makeText(getApplicationContext(), response.body().message, Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Error in response", Toast.LENGTH_LONG).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<RegisterResponse> call, Throwable t) {
//                load_pb.setVisibility(View.GONE);
//                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
//            }
        });
    }
}