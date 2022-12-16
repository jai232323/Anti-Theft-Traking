package com.example.antitest_traking.ComplaintsReceivingActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.antitest_traking.Module.VerifiedComplaintsData;
import com.example.antitest_traking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ComplaintsReceivingActivity extends AppCompatActivity {


    TextView MissingItem;

    FirebaseUser firebaseUser;
    String useridkey,userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_receiving);


        MissingItem=findViewById(R.id.MissingItem);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        SharedPreferences prefs1 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        useridkey=prefs1.getString("useridkey","none");

//        SharedPreferences prefs2 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
//        userid=prefs1.getString("userid","none");

        getComplaintsData();
    }

    private void getComplaintsData() {
        DatabaseReference reference  = FirebaseDatabase.getInstance().getReference("AdminSendToUser").child(useridkey);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                VerifiedComplaintsData data=snapshot.getValue(VerifiedComplaintsData.class);

                if (!snapshot.exists()){
                    MissingItem.setVisibility(View.GONE);
                }

                else if (useridkey.equals(useridkey)){
                    MissingItem.setVisibility(View.VISIBLE);
                    MissingItem.setText(data.getUserName()+ "\n"+
                            "Your MissingItem Location : \n"+data.getComplaintItemLocation());
                }
//                else {
//                    MissingItem.setVisibility(View.GONE);
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}