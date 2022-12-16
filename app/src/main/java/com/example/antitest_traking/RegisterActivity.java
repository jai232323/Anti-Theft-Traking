package com.example.antitest_traking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    ImageView UserImage;
    EditText UserName,UserEmail,UserPassword,UserConfirmPassword,UserPhoneNumber,UserAddress;
    Button RegisterBtn;

    TextView Register_txt_signup;


    private FirebaseAuth auth;
    private StorageReference storageReference;

    private DatabaseReference reference;
    private ProgressDialog pd;


    private final int REQ = 1;
    private Bitmap bitmap;


    String Name,Email,Password,ConfirmPassword,PhoneNumber,Address,downloadUrl = "img";
    String userid;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null){
            startActivity(new Intent(RegisterActivity.this,UserMainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserImage=findViewById(R.id.UserImage);
        UserName=findViewById(R.id.UserName);
        UserEmail=findViewById(R.id.UserEmail);
        UserPassword=findViewById(R.id.UserPassword);
        UserConfirmPassword=findViewById(R.id.UserConfirmPassword);
        UserPhoneNumber=findViewById(R.id.UserPhoneNumber);
        UserAddress=findViewById(R.id.UserAddress);
        RegisterBtn=findViewById(R.id.RegisterBtn);

        Register_txt_signup=findViewById(R.id.Register_txt_signup);
        Register_txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });


        pd = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap==null){
                    Toast.makeText(RegisterActivity.this,"Set Your Image",Toast.LENGTH_SHORT).show();

                }else {
                    CheckValidations();

                }
            }
        });


        UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpneGallery();
            }
        });
    }

    private void CheckValidations() {

        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        UserEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Email.matches(emailpattern) && s.length()>0){
                  //  Toast.makeText(RegisterActivity.this, "Valid Email Address", Toast.LENGTH_SHORT).show();
                }else {
                   // Toast.makeText(RegisterActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });

         Name = UserName.getText().toString();
         Email = UserEmail.getText().toString();
         Password = UserPassword.getText().toString();
         ConfirmPassword = UserConfirmPassword.getText().toString();
         PhoneNumber = UserPhoneNumber.getText().toString();
         Address = UserAddress.getText().toString();

         if (Name.isEmpty()){
            UserName.setError("Name Empty");
            UserName.requestFocus();
        }
        else if (Email.isEmpty()){
            UserEmail.setError("Email Empty");
            UserEmail.requestFocus();
        }else if (Password.isEmpty()){
            UserPassword.setError("Password Empty");
            UserPassword.requestFocus();
        } else if (Password.length() < 6 )
         {
             Toast.makeText(RegisterActivity.this,"Password must have 6 Characters" , Toast.LENGTH_SHORT).show();
         }else if (ConfirmPassword.isEmpty()){
            UserConfirmPassword.setError("Confirm Password Empty");
            UserConfirmPassword.requestFocus();
        } else if (ConfirmPassword.length() < 6 )
         {
             Toast.makeText(RegisterActivity.this,"Password must have 6 Characters" , Toast.LENGTH_SHORT).show();
         }else if (!Password.equals(ConfirmPassword)){
            Toast.makeText(RegisterActivity.this,"Password\nConfirm Password Wrong",Toast.LENGTH_SHORT).show();
        }
        else if (PhoneNumber.isEmpty()){
            UserPhoneNumber.setError("Phone Number Empty");
            UserPhoneNumber.requestFocus();
        }else if (PhoneNumber.length() < 10){
            Toast.makeText(RegisterActivity.this,"Phone Number Must Have 10 Numbers" , Toast.LENGTH_SHORT).show();
        }else if (PhoneNumber.length() > 10){
            Toast.makeText(RegisterActivity.this,"Phone Number Must Have 10 Numbers" , Toast.LENGTH_SHORT).show();
        }else if (Address.isEmpty()){
            UserAddress.setError("Address Empty");
            UserAddress.requestFocus();
        }else {
            UploadImage();
        }
    }

    private void UploadImage() {
        pd.setMessage("Uploading\nPlease Wait...");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        final StorageReference filePath;
        filePath=storageReference.child("Users").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertDate(Email,Password);
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertDate(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this , new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {



                FirebaseUser firebaseUser1 = auth.getCurrentUser();
                userid = firebaseUser1.getUid();

                HashMap<String, String> hashMap = new HashMap<>();



                reference= FirebaseDatabase.getInstance().getReference("Users");
                hashMap.put("UserId", userid);
                hashMap.put("UserName", Name);
                hashMap.put("UserEmail", Email);
                hashMap.put("UserPassword", Password);
                hashMap.put("UserConfirmPassword", ConfirmPassword);
                hashMap.put("UserPhoneNumber", PhoneNumber);
                hashMap.put("UserAddress", Address);
                hashMap.put("UserImageUrl", downloadUrl);

                reference.child(userid).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Intent intent = new Intent(RegisterActivity.this, UserMainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                            editor.putString("userid", userid);
                            editor.apply();

                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this,"You can't register",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void OpneGallery() {
        Intent picImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UserImage.setImageBitmap(bitmap);
        }
    }
}