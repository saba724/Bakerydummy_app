package com.example.bakeryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import static com.example.bakeryapp.R.id.menu_cart;

public class HomePage extends AppCompatActivity {
   // ViewFlipper viewFlipper;
    BottomNavigationView bottomNavigationView;
    //View cartmenue;

//    BottomSheet bottomSheet=BottomSheet.newInstance();
//    bottomSheet.show(getSupportFragmentManager(),"saba");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
       // cartmenue=findViewById(R.id.menu_order);
//        cartmenue.setOnClickListener(new View.OnClickListener() { //bottom sheet
//            @Override
//            public void onClick(View v) {
//                BottomSheet bottomSheet=BottomSheet.newInstance();
//                bottomSheet.show(getSupportFragmentManager(),"saba");
//            }
//        });
        //loading home fragment by default in homepage activity

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();

        if(getIntent().hasExtra("cart")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new CartFragment()).commit();
        }

        //bottom navigation view
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp_fragment=null;
                switch (item.getItemId()){
                    case R.id.menu_home : temp_fragment=new HomeFragment();
                    break;
                    case R.id.menu_order: temp_fragment=new OrderFragment();
                    break;
                    case menu_cart : temp_fragment=new CartFragment();
//                        if(CartFragment.class.isSynthetic()){
//
//                            BottomSheet bottomSheet=new BottomSheet();
//                          bottomSheet.show(getSupportFragmentManager(),"saba");
//
//                        }
                        break;
                    case R.id.menu_profile: temp_fragment=new ProfileFragment();
                        break;
                    default:
                        Toast.makeText(HomePage.this,"default switch block",Toast.LENGTH_LONG).show();
                        //throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,temp_fragment).commit();
                return true;

            }
        });

    }
//    public void showImages(int images){
//        ImageView imageView=new ImageView(this);
//        imageView.setBackgroundResource(images);
//        viewFlipper.addView(imageView);
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(2000);
//        //animation
//        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
//        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
//
//
//    }

}