package com.example.labs2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.MyViewClass> {
    ArrayList<String> name;
    ArrayList<String> email;
    ArrayList<String> info;
    private OnItemClickListener onItemClickListener;
    Context context;

    public HelperAdapter(ArrayList<String> name, ArrayList<String> email,ArrayList<String> info, Context context) {
        this.name = name;
        this.email = email;
        this.context = context;
        this.info = info;
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        MyViewClass myViewClass = new MyViewClass(view);
        return myViewClass;
    }

    public interface OnItemClickListener {
        void onItemClick(String name, String email,String info);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.name.setText(name.get(position));
        holder.email.setText(email.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && onItemClickListener != null) {
                    onItemClickListener.onItemClick(name.get(adapterPosition), email.get(adapterPosition),info.get(adapterPosition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
    }
}
