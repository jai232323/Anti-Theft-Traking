package com.example.antitest_traking.Admin;

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
import com.example.antitest_traking.Admin.Adapter.UserAdminAdapter;
import com.example.antitest_traking.Module.ComplaintsData;
import com.example.antitest_traking.Module.VerifiedComplaintsData;
import com.example.antitest_traking.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ThirdPartyResponseAdapter extends RecyclerView.Adapter<ThirdPartyResponseAdapter.ViewHolder> {

    Context context;
    ArrayList<VerifiedComplaintsData> list;

    public ThirdPartyResponseAdapter(Context context, ArrayList<VerifiedComplaintsData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.thirdparty_response_adapter,parent,false);

        return new ThirdPartyResponseAdapter.ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VerifiedComplaintsData data = list.get(position);
        holder.UserName.setText(data.getUserName());
        holder.UserAddress.setText(data.getUserAddress());
        holder.ComplaintMissingItem.setText(data.getComplaintItemLocation());
        holder.UserPhoneNumber.setText(data.getUserPhoneNumber());

        try {
            if (data.getUserImageUrl() !=null)
                Glide.with(context).load(data.getUserImageUrl()).into(holder.UserImage);

        }catch (Exception e){
            e.printStackTrace();
        }
        holder.ShareDataToThirdParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AdminSendToUser");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        HashMap<String ,Object> hashMap = new HashMap<>();

                        hashMap.put("useridkey",data.getUseridkey());
                        hashMap.put("UserImageUrl",data.getUserImageUrl());
                        hashMap.put("userid",data.getUserid());
                        hashMap.put("UserName",data.getUserName());
                        hashMap.put("UserPhoneNumber",data.getUserPhoneNumber());
                        hashMap.put("UserAddress",data.getUserAddress());
                        hashMap.put("MissingItem",data.getMissingItem());
                        hashMap.put("ComplaintItemLocation",data.getComplaintItemLocation());

                        reference.child(data.getUseridkey()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                SharedPreferences.Editor editor = context.getSharedPreferences("PREFS",Context.MODE_PRIVATE).edit();
                                editor.putString("useridkey", data.getUseridkey());
                                editor.apply();

                                Toast.makeText(context,"Data Sended Successfully",Toast.LENGTH_SHORT).show();
                                holder.DataVisibleThirdPartyResponse.setVisibility(View.VISIBLE);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
                                holder.DataVisibleThirdPartyResponse.setVisibility(View.GONE);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView UserImage;
        TextView UserName,UserAddress,UserPhoneNumber,ComplaintMissingItem;
        MaterialCardView ShareDataToThirdParty;
        TextView DataVisibleThirdPartyResponse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            UserImage=itemView.findViewById(R.id.UserImage);
            UserName=itemView.findViewById(R.id.UserName);
            UserAddress=itemView.findViewById(R.id.UserAddress);
            UserPhoneNumber=itemView.findViewById(R.id.UserPhoneNumber);
            ComplaintMissingItem=itemView.findViewById(R.id.ComplaintMissingItem);
            ShareDataToThirdParty=itemView.findViewById(R.id.ShareDataToThirdParty);
            DataVisibleThirdPartyResponse=itemView.findViewById(R.id.DataVisibleThirdPartyResponse);
        }
    }
}
