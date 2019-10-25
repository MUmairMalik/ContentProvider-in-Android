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

    List<String> name;
    List<String> number;
    public RAdapter(List<String> nam, List<String> numb) {
        name=nam;
        number=numb;
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
                String title=name.get(position);
                holder.naam.setText(title);
                title = number.get(position);
                holder.num.setText(title);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class RViewHolder extends RecyclerView.ViewHolder{
           ImageView Iv;
           EditText naam;
           EditText num;



            public RViewHolder(@NonNull View itemView) {
                super(itemView);
                naam=itemView.findViewById(R.id.nameTxt);
                num=itemView.findViewById(R.id.numTxt);


            }
        }

}
