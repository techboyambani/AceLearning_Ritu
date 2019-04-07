package com.example.dell.acelearning;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class select_test_adapter extends RecyclerView.Adapter<select_test_adapter.ViewHolder> {
    TextView subjects;
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> subs=new ArrayList<>();
    ArrayList<String> items= new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.tests,parent,false);
        return new ViewHolder(view);
    }
    public void update(String test_name,String subject_value )
    {
        items.add(test_name);
        subs.add(subject_value);
        notifyDataSetChanged();
    }

    public select_test_adapter(RecyclerView recyclerView, Context context, ArrayList<String> items,ArrayList<String> subs) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.subs=subs;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tests.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tests;
        public ViewHolder(View itemView){
            super(itemView);
            tests=itemView.findViewById(R.id.card_test);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=recyclerView.getChildLayoutPosition(v);
                    Intent i= new Intent(v.getContext(), student_test.class);
                    i.putExtra("subject", subs.get(position));
                    i.putExtra("test_name",items.get(position));
                    context.startActivity(i);
                }
            });
        }
    }
}

