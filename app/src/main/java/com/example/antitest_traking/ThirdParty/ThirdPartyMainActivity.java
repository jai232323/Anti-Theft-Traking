package com.example.antitest_traking.ThirdParty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.antitest_traking.Admin.Adapter.UserAdminAdapter;
import com.example.antitest_traking.Admin.UserAdmiMainActivity;
import com.example.antitest_traking.Module.ThirdPartyData;
import com.example.antitest_traking.Module.UserData;
import com.example.antitest_traking.R;
import com.example.antitest_traking.ThirdParty.Adapter.ThirdPartyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ThirdPartyMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout ThirdPartyFormData;

    private ArrayList<ThirdPartyData> list;
    private ThirdPartyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ThirdParty");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.ThirdPartyRecyclerView);
        ThirdPartyFormData=findViewById(R.id.ThirdPartyFormData);

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(ThirdPartyMainActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new ThirdPartyAdapter(ThirdPartyMainActivity.this,list);
        recyclerView.setAdapter(adapter);


        getThirdPartyInfo();
    }

    private void getThirdPartyInfo() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("ThirdParty");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()){
                    ThirdPartyFormData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }else {
                    ThirdPartyFormData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ThirdPartyData data = dataSnapshot.getValue(ThirdPartyData.class);
                        list.add(0, data);
                    }

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}