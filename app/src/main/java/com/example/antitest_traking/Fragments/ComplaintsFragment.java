package com.example.antitest_traking.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.antitest_traking.ComplaintsActivity.ComplaintsActivity;
import com.example.antitest_traking.ComplaintsReceivingActivity.ComplaintsReceivingActivity;
import com.example.antitest_traking.R;
import com.google.android.material.card.MaterialCardView;


public class ComplaintsFragment extends Fragment {


    MaterialCardView Complaints,ComplaintsReceiving;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);


        Complaints=view.findViewById(R.id.Complaints);
        ComplaintsReceiving=view.findViewById(R.id.ComplaintsReceiving);

        Complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ComplaintsActivity.class));
            }
        });
        ComplaintsReceiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ComplaintsReceivingActivity.class));
            }
        });

        return view;
    }
}