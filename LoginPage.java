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
import com.example.bakeryapp.data.LoginRespone;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {
    Button login,register,hp,skip;
    EditText email,password;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //shared prenfrences
        if(!SharedPreference.getInstance(getApplicationContext()).getString(SharedPreference.USER_ID).equals("")){
            startActivity(new Intent(getApplicationContext(), HomePage.class));
            finishAffinity();
        }
        register=(Button)findViewById(R.id.register_b);
        progressBar=findViewById(R.id.l_loader);
        skip=findViewById(R.id.skip);
        email=findViewById(R.id.editTextEmailAddress);
        password=findViewById(R.id.edittext_pass);
        relativeLayout=findViewById(R.id.relative_login);
        login=(Button)findViewById(R.id.loginbutton);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginPage.this,HomePage.class);
                startActivity(intent);
            }
        });
       // hp=findViewById(R.id.register_h);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginPage.this,HomePage.class);
                startActivity(intent);
            }
        });
//        hp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(LoginPage.this,HomePage.class);
//                startActivity(intent);
//            }
//        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.length()==0){
                    email.setError("Enter Email");
                    email.requestFocus();
                    return;
                }
                if(password.length()==0){
                    password.setError("Enter password");
                    password.requestFocus();
                    return;
                }
                loginProcessApi();

            }
        });
    }
    public void loginProcessApi(){
        progressBar.setVisibility(View.VISIBLE);

        RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
        Call<LoginRespone> call=requestApi.login(email.getText().toString().trim(),password.getText().toString().trim());
        call.enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                progressBar.setVisibility(View.GONE);

                if(response.isSuccessful()) {
                    if (response.body().error.equalsIgnoreCase("0")) {
                        //Toast.makeText(LoginPage.this,response.body().message,Toast.LENGTH_LONG).show();
                        Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
                        Intent intent=new Intent(LoginPage.this,HomePage.class);
                        SharedPreference.getInstance(getApplicationContext()).saveString(SharedPreference.USER_ID, email.getText().toString());
                        startActivity(intent);
                        finishAffinity();
                    } else {
                         //Toast.makeText(LoginPage.this,response.body().message,Toast.LENGTH_LONG).show();
                        Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

                Snackbar.make(relativeLayout,t.getMessage(),Snackbar.LENGTH_LONG).show();

                 //Toast.makeText(LoginPage.this,t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });
    }
}