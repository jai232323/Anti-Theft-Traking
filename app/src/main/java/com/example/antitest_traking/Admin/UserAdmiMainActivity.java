package com.example.antitest_traking.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.antitest_traking.Admin.Adapter.UserAdminAdapter;
import com.example.antitest_traking.Module.UserData;
import com.example.antitest_traking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserAdmiMainActivity extends AppCompatActivity {


     RecyclerView recyclerView;
     LinearLayout UserFormData;

    private ArrayList<UserData> list;
    private UserAdminAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admi_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("UserForms");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.UserFormRecyclerView);
        UserFormData=findViewById(R.id.UserFormData);




        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(UserAdmiMainActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new UserAdminAdapter(UserAdmiMainActivity.this,list);
        recyclerView.setAdapter(adapter);


        getUserInfo();
    }

    private void getUserInfo() {
        DatabaseReference reference1 =FirebaseDatabase.getInstance().getReference("Users");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if (!snapshot.exists()){
                    UserFormData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }else {
                    UserFormData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        UserData data = dataSnapshot.getValue(UserData.class);
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