package com.example.antitest_traking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antitest_traking.Admin.AdminMainActivity;
import com.example.antitest_traking.ThirdParty.ThirdPartyMainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ThirdPartyEmailActivity extends AppCompatActivity {

    EditText ThirdPartyEmail,ThirdPartyPassword;
    Button ThirdPartyLoginBtn;


//    FirebaseUser firebaseUser;
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (firebaseUser != null){
//            startActivity(new Intent(ThirdPartyEmailActivity.this,ThirdPartyMainActivity.class));
//            finish();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_email);

        ThirdPartyEmail=findViewById(R.id.ThirdPartyEmail);
        ThirdPartyPassword=findViewById(R.id.ThirdPartyPassword);
        ThirdPartyLoginBtn=findViewById(R.id.ThirdPartyLogin);

        ThirdPartyLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thirdpartyEMail = ThirdPartyEmail.getText().toString();
                String thirdpartyPassword =ThirdPartyPassword.getText().toString();

                if (thirdpartyEMail.isEmpty()){
                    ThirdPartyEmail.setError("Email Empty");
                    ThirdPartyEmail.requestFocus();
                }else if (thirdpartyPassword.isEmpty()){
                    ThirdPartyPassword.setError("Password Empty");
                    ThirdPartyPassword.requestFocus();
                }else if (thirdpartyEMail.equals("thirdparty23@gmail.com")&& thirdpartyPassword.equals("thirdparty23")){
                    Toast.makeText(ThirdPartyEmailActivity.this,"Third Party Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ThirdPartyEmailActivity.this, ThirdPartyMainActivity.class));
                }else {
                    Toast.makeText(ThirdPartyEmailActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}