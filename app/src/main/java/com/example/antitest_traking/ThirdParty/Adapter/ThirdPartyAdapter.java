package com.example.antitest_traking.ThirdParty.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.antitest_traking.Admin.Adapter.ComplaintsAdminAdapter;
import com.example.antitest_traking.Module.ComplaintsData;
import com.example.antitest_traking.Module.ThirdPartyData;
import com.example.antitest_traking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ThirdPartyAdapter extends RecyclerView.Adapter<ThirdPartyAdapter.ViewHolder> {


    Context context;
    ArrayList<ThirdPartyData> list;

    public ThirdPartyAdapter(Context context, ArrayList<ThirdPartyData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.thirdparty_adapter,parent,false);

        return new ThirdPartyAdapter.ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThirdPartyData data = list.get(position);
        holder.UserName.setText(data.getUserName());
        holder.UserAddress.setText(data.getUserAddress());
        holder.ComplaintMissingItem.setText(data.getMissingItems());
        holder.UserPhoneNumber.setText(data.getUserPhoneNumber());

        try {
            if (data.getUserImageUrl() !=null)
                Glide.with(context).load(data.getUserImageUrl()).into(holder.UserImage);

        }catch (Exception e){
            e.printStackTrace();
        }


        holder.Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ComplaintsItem = holder.MissingItemsComplaints.getText().toString();

                if (ComplaintsItem.isEmpty()){
                    holder.MissingItemsComplaints.setError("Provide Something");
                    holder.MissingItemsComplaints.requestFocus();
                }else {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ThirdPartyVerifiedComplaints");

                    HashMap<String,String> hashMap =new HashMap<>();
                    hashMap.put("useridkey",data.getUseridkey());
                    hashMap.put("UserImageUrl",data.getUserImageUrl());
                    hashMap.put("userid",data.getUserid());
                    hashMap.put("UserName",data.getUserName());
                    hashMap.put("UserPhoneNumber",data.getUserPhoneNumber());
                    hashMap.put("UserAddress",data.getUserAddress());
                    hashMap.put("MissingItem",data.getMissingItems());
                    hashMap.put("ComplaintItemLocation",ComplaintsItem);

                    reference.child(data.getUseridkey()).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

//                            SharedPreferences.Editor editor = context.getSharedPreferences("PREFS",context.MODE_PRIVATE).edit();
//                            editor.putString("useridkey", data.getUseridkey());
//                            editor.apply();

                            Toast.makeText(context,"Verified Successfully",Toast.LENGTH_SHORT).show();
                            holder.DataVerified.setVisibility(View.VISIBLE);


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context,"Something Wrong",Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView UserImage;
        TextView UserName,UserAddress,UserPhoneNumber,ComplaintMissingItem;
        EditText MissingItemsComplaints;
        Button Submit;
        TextView DataVerified;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            UserImage=itemView.findViewById(R.id.UserImage);
            UserName=itemView.findViewById(R.id.UserName);
            UserAddress=itemView.findViewById(R.id.UserAddress);
            UserPhoneNumber=itemView.findViewById(R.id.UserPhoneNumber);
            ComplaintMissingItem=itemView.findViewById(R.id.ComplaintMissingItem);
            MissingItemsComplaints=itemView.findViewById(R.id.MissingItemsLocation);
            Submit=itemView.findViewById(R.id.Submit);
            DataVerified=itemView.findViewById(R.id.DataVerified);
        }
    }
}
