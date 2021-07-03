package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //variables
    Animation top_anim,bottom_anim;
    ImageView image;
    TextView name,tagline;
    //splash_screen
    public static int SPLASH_SCREEN=3000;//6 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //removing status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //calling animation
        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //casting
        image=(ImageView)findViewById(R.id.front_image);
        name=(TextView)findViewById(R.id.bak_name);
        tagline=(TextView)findViewById(R.id.bk_tagline);
        //setting animation
        image.setAnimation(top_anim);
        name.setAnimation(bottom_anim);
        tagline.setAnimation(bottom_anim);
        //creating delay method
        //handler handle the delay process
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,LoginPage.class);
                startActivity(intent);
                //finish();

            }
        },SPLASH_SCREEN);




    }
}