package com.example.antitest_traking.Admin.Adapter;

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
import com.example.antitest_traking.Module.ComplaintsData;
import com.example.antitest_traking.Module.UserData;
import com.example.antitest_traking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ComplaintsAdminAdapter extends RecyclerView.Adapter<ComplaintsAdminAdapter.ViewHolder> {

    Context context;
    ArrayList<ComplaintsData> list;

    public ComplaintsAdminAdapter(Context context, ArrayList<ComplaintsData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ComplaintsAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.complaints_form_adapter,parent,false);

        return new ComplaintsAdminAdapter.ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintsAdminAdapter.ViewHolder holder, int position) {
        ComplaintsData data = list.get(position);
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

        holder.ShareDataToThirdParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ThirdParty");

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
                        hashMap.put("MissingItems",data.getMissingItems());

                        reference.child(data.getUseridkey()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context,"Data Sended Successfully",Toast.LENGTH_SHORT).show();

                                holder.DataVisible.setVisibility(View.VISIBLE);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
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
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView UserImage;
        TextView UserName,UserAddress,UserPhoneNumber,ComplaintMissingItem;
        EditText MissingItemsComplaints;
        Button Submit;
        MaterialCardView ShareDataToThirdParty;
        TextView DataVisible;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            UserImage=itemView.findViewById(R.id.UserImage);
            UserName=itemView.findViewById(R.id.UserName);
            UserAddress=itemView.findViewById(R.id.UserAddress);
            UserPhoneNumber=itemView.findViewById(R.id.UserPhoneNumber);
            ComplaintMissingItem=itemView.findViewById(R.id.ComplaintMissingItem);
            ShareDataToThirdParty=itemView.findViewById(R.id.ShareDataToThirdParty);
            DataVisible=itemView.findViewById(R.id.DataVisible);

//            MissingItemsComplaints=itemView.findViewById(R.id.MissingItemsComplaints);
//            Submit=itemView.findViewById(R.id.Submit);
        }
    }
}
