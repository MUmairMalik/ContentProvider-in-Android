package com.example.assignmen3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.RViewHolder>{

    List<Contacts> contactss;
    public RAdapter(List<Contacts> c) {
        contactss=c;
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,parent,false);
        return new RViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
                String title=contactss.get(position).name;
                holder.naam.setText(title);
                title =contactss.get(position).phone ;
                holder.num.setText(title);
    }

    @Override
    public int getItemCount() {
        return contactss.size();
    }

    public class RViewHolder extends RecyclerView.ViewHolder{
           ImageView Iv;
           EditText naam;
           EditText num;



            public RViewHolder(@NonNull View itemView) {
                super(itemView);
                Iv=itemView.findViewById(R.id.imgIcon);
                naam=itemView.findViewById(R.id.nameTxt);
                num=itemView.findViewById(R.id.numTxt);


            }
        }

}
