package com.example.antitest_traking.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.antitest_traking.R;
import com.example.antitest_traking.ThirdPartyEmailActivity;
import com.example.antitest_traking.ThirdPartyResponseActivity;
import com.google.android.material.card.MaterialCardView;

public class AdminMainActivity extends AppCompatActivity {

    private MaterialCardView UserAdmin,ComplaintsAdmin,ThirdPartyResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        UserAdmin=findViewById(R.id.UserAdmin);
        ComplaintsAdmin=findViewById(R.id.ComplaintsAdmin);
        ThirdPartyResponse=findViewById(R.id.ThirdPartyResponse);

        UserAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this,UserAdmiMainActivity.class));
            }
        });
        ComplaintsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this,ComplaintsAdmiMainActivity.class));
            }
        });
        ThirdPartyResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminMainActivity.this, ThirdPartyResponseActivity.class));
            }
        });
    }
}