package com.example.antitest_traking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.antitest_traking.Admin.Adapter.UserAdminAdapter;
import com.example.antitest_traking.Admin.ThirdPartyResponseAdapter;
import com.example.antitest_traking.Admin.UserAdmiMainActivity;
import com.example.antitest_traking.Module.UserData;
import com.example.antitest_traking.Module.VerifiedComplaintsData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ThirdPartyResponseActivity extends AppCompatActivity {

    RecyclerView ThirdPartyResponseRecyclerView;
    LinearLayout ThirdPartyResponseData;

    private ArrayList<VerifiedComplaintsData> list;
    private ThirdPartyResponseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_response);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Third Party Response");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ThirdPartyResponseRecyclerView=findViewById(R.id.ThirdPartyResponseRecyclerView);
        ThirdPartyResponseData=findViewById(R.id.ThirdPartyResponseData);




        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(ThirdPartyResponseActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        ThirdPartyResponseRecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new ThirdPartyResponseAdapter(ThirdPartyResponseActivity.this,list);
        ThirdPartyResponseRecyclerView.setAdapter(adapter);


        getThirdPartyInfo();

    }

    private void getThirdPartyInfo() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("ThirdPartyVerifiedComplaints");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()){
                    ThirdPartyResponseData.setVisibility(View.VISIBLE);
                    ThirdPartyResponseRecyclerView.setVisibility(View.GONE);
                }else {
                    ThirdPartyResponseData.setVisibility(View.GONE);
                    ThirdPartyResponseRecyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        VerifiedComplaintsData data = dataSnapshot.getValue(VerifiedComplaintsData.class);
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