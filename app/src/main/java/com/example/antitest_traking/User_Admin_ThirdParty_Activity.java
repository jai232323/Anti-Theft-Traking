package com.example.antitest_traking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class User_Admin_ThirdParty_Activity extends AppCompatActivity {


    MaterialCardView User,Admin,ThirdParty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);

        User=findViewById(R.id.User);
        Admin=findViewById(R.id.Admin);
        ThirdParty=findViewById(R.id.ThirdParty1);

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Admin_ThirdParty_Activity.this,Reg_LoginActivity.class));
            }
        });
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Admin_ThirdParty_Activity.this,AdminEmailActivity.class));

            }
        });
        ThirdParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(User_Admin_ThirdParty_Activity.this,ThirdPartyEmailActivity.class));
              //  Toast.makeText(User_Admin_ThirdParty_Activity.this,"Sended",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(User_Admin_ThirdParty_Activity.this,ThirdPartyEmailActivity.class);
                startActivity(intent);
            }
        });
    }
}