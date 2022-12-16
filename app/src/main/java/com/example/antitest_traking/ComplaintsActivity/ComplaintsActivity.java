package com.example.antitest_traking.ComplaintsActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.antitest_traking.Module.UserData;
import com.example.antitest_traking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ComplaintsActivity extends AppCompatActivity {


    ImageView UserImage;
    Spinner MissingItems;
    EditText UserName,UserPhoneNumber,UserAddress;
    Button Submit;

    String Itesms,Name,PhoneNumber,Address;
    String userid;

    DatabaseReference reference,reference1;

    UserData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        MissingItems=findViewById(R.id.MissingItems);
        UserName=findViewById(R.id.UserName);
        UserPhoneNumber=findViewById(R.id.UserPhoneNumber);
        UserAddress=findViewById(R.id.UserAddress);
        Submit=findViewById(R.id.Submit);
        UserImage=findViewById(R.id.UserImage);

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference1 = FirebaseDatabase.getInstance().getReference("Complaints");


        SharedPreferences prefs1 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userid=prefs1.getString("userid","none");

        reference = FirebaseDatabase.getInstance().getReference("Users")
                .child(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                data = snapshot.getValue(UserData.class);

                Glide.with(getApplicationContext()).load(data.getUserImageUrl()).into(UserImage);
                UserName.setText(data.getUserName());
                UserPhoneNumber.setText(data.getUserPhoneNumber());
                UserAddress.setText(data.getUserAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        String[] items = new String[]{"Select MissingItem","Vehicle","Laptop","Watch",
                "Moneypars","MobilePhone","Bag"};

        MissingItems.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));

        MissingItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Itesms=MissingItems.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Name=UserName.getText().toString();
                PhoneNumber=UserPhoneNumber.getText().toString();
                Address=UserAddress.getText().toString();

                if (Itesms.equals("Select MissingItem")){
                    Toast.makeText(ComplaintsActivity.this,"Please Provide Missing Items" , Toast.LENGTH_SHORT).show();
                }else {
                    CheckValidations();
                }

            }
        });
    }



    private void CheckValidations() {
        if (Name.isEmpty()){
            UserName.setError("Provide Name");
            UserName.requestFocus();
        }else if (PhoneNumber.isEmpty()){
            UserPhoneNumber.setError("Provide Phone Number");
            UserPhoneNumber.requestFocus();
        }else if (Address.isEmpty()){
            UserAddress.setError("Provide Address");
            UserAddress.requestFocus();
        }else {
            UploadData();
        }
    }

    private void UploadData() {


        String key=reference.push().getKey();

        HashMap<String,String> hashMap =new HashMap<>();
        hashMap.put("useridkey",userid+key);
        hashMap.put("UserImageUrl",data.getUserImageUrl());
        hashMap.put("userid",userid);
        hashMap.put("UserName",Name);
        hashMap.put("UserPhoneNumber",PhoneNumber);
        hashMap.put("UserAddress",Address);
        hashMap.put("MissingItem",Itesms);

        reference1.child(userid+key).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(ComplaintsActivity.this,"Complaints Registered Successfully",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ComplaintsActivity.this,"Something Wrong!!!",Toast.LENGTH_SHORT).show();

            }
        });

    }
}