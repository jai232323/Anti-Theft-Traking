package com.example.antitest_traking.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.antitest_traking.Module.UserData;
import com.example.antitest_traking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserPrfofileFragment extends Fragment {

    String userid;

    TextView Name,EMail,PhoneNumber,Address;
    ImageView Imageurl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_prfofile, container, false);


        Name=view.findViewById(R.id.UserName);
        EMail=view.findViewById(R.id.UserEmail);
        PhoneNumber=view.findViewById(R.id.UserPhoneNumber);
        Address=view.findViewById(R.id.UserAddress);
        Imageurl=view.findViewById(R.id.UserImage);


        SharedPreferences prefs1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userid=prefs1.getString("userid","none");



        getUserData();

        return view;
    }

    private void getUserData() {

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users")
                .child(userid);

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((getContext() == null)){
                    return;
                }
                UserData data = snapshot.getValue(UserData.class);



                Glide.with(getContext()).load(data.getUserImageUrl()).into(Imageurl);
                Imageurl.setVisibility(View.VISIBLE);

                Name.setText(data.getUserName());
                Name.setVisibility(View.VISIBLE);
                EMail.setText(data.getUserEmail());
                EMail.setVisibility(View.VISIBLE);
                PhoneNumber.setText(data.getUserPhoneNumber());
                PhoneNumber.setVisibility(View.VISIBLE);
                Address.setText(data.getUserAddress());
                Address.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}