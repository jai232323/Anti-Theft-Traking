package com.example.antitest_traking.Admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.antitest_traking.Module.UserData;
import com.example.antitest_traking.R;

import java.util.ArrayList;

public class UserAdminAdapter extends RecyclerView.Adapter<UserAdminAdapter.ViewHolder> {


     Context context;
     ArrayList<UserData> list;

    public UserAdminAdapter(Context context, ArrayList<UserData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.user_form_adapter,parent,false);

       return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserData data = list.get(position);
        holder.UserName.setText(data.getUserName());
        holder.UserAddress.setText(data.getUserAddress());
        holder.UserEmail.setText(data.getUserEmail());
        holder.UserPhoneNumber.setText(data.getUserPhoneNumber());

        try {
            if (data.getUserImageUrl() !=null)
                Glide.with(context).load(data.getUserImageUrl()).into(holder.UserImage);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView UserImage;
        TextView UserName,UserEmail,UserAddress,UserPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            UserImage=itemView.findViewById(R.id.UserImage);
            UserName=itemView.findViewById(R.id.UserName);
            UserEmail=itemView.findViewById(R.id.UserEmail);
            UserAddress=itemView.findViewById(R.id.UserAddress);
            UserPhoneNumber=itemView.findViewById(R.id.UserPhoneNumber);
        }
    }
}
