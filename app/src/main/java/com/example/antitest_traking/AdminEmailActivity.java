package com.example.antitest_traking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antitest_traking.Admin.AdminMainActivity;
import com.example.antitest_traking.Admin.UserAdmiMainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminEmailActivity extends AppCompatActivity {


    EditText admin_email,admin_password;
    Button admin_login;


//    FirebaseUser firebaseUser;
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (firebaseUser != null){
//            startActivity(new Intent(AdminEmailActivity.this, AdminMainActivity.class));
//            finish();
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_email);


        admin_email=findViewById(R.id.AdminEmail);
        admin_password=findViewById(R.id.AdminPassword);
        admin_login=findViewById(R.id.admin_login);

        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = admin_email.getText().toString();
                String password = admin_password.getText().toString();

                if (email.isEmpty()){
                    admin_email.setError("Email Empty");
                    admin_email.requestFocus();
                }else if (password.isEmpty()){
                    admin_password.setError("Password Empty");
                    admin_password.requestFocus();
                }else if (email.equals("admin23@gmail.com") & password.equals("admin323")){
                    Toast.makeText(AdminEmailActivity.this,"Admin Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminEmailActivity.this, AdminMainActivity.class));
                    finish();
                }else {
                    Toast.makeText(AdminEmailActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}