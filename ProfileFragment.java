package com.example.bakeryapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakeryapp.api.RequestApi;
import com.example.bakeryapp.api.RetrofitClass;
import com.example.bakeryapp.data.LoginRespone;
import com.example.bakeryapp.data.ProfileResponse;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ProfileFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ProfileFragment extends Fragment {
    TextView name, phone, city, email, address, dob;
    Button logout;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public ProfileFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ProfileFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ProfileFragment newInstance(String param1, String param2) {
//        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false);
//    }
//}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        name=view.findViewById(R.id.p_name);
        phone=view.findViewById(R.id.p_mobile);
        city=view.findViewById(R.id.p_city);
        email=view.findViewById(R.id.p_email);
       // password=view.findViewById(R.id.p_password);
        address=view.findViewById(R.id.p_address);
        dob=view.findViewById(R.id.p_dob);
        logout=view.findViewById(R.id.profilelogout);logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to log out?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // getContext().super.onBackPressed(); //coming out from the activity like exit
                                SharedPreference.getInstance(getActivity()).logoutUser(getActivity());
                            }
                        })
                        .setNegativeButton("Cancel", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        profileFetchingApi();





        return view;
    }
    public void profileFetchingApi(){
        RequestApi requestApi= RetrofitClass.getClient().create(RequestApi.class);
        Call<ProfileResponse> call=requestApi.get_profile(SharedPreference.getInstance(getContext()).getString(SharedPreference.USER_ID));
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
               // progressBar.setVisibility(View.GONE);

                if(response.isSuccessful()) {
                    name.setText(response.body().getName());
                    phone.setText(response.body().getMobile());
                    city.setText(response.body().getCity());
                    email.setText(response.body().getEmail());
//                    password.setText(response.body().getPassword());
                    address.setText(response.body().getAddress());
                    dob.setText(response.body().getDob());

                   // if (response.body().error.equalsIgnoreCase("0")) {
                        //Toast.makeText(getActivity(),response.body().message,Toast.LENGTH_LONG).show();
                        //Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
//                        Intent intent=new Intent(ProfileFragment.this,HomePage.class);
//                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(),response.body().message,Toast.LENGTH_LONG).show();
                       // Snackbar.make(relativeLayout, response.body().message, Snackbar.LENGTH_LONG).show();
                    }
                }
           // }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                //progressBar.setVisibility(View.GONE);

                //Snackbar.make(relativeLayout,t.getMessage(),Snackbar.LENGTH_LONG).show();

                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();


            }
        });
    }
}