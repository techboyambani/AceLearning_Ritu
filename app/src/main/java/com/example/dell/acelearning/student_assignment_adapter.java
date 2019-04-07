package com.example.dell.acelearning;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class student_assignment_adapter extends RecyclerView.Adapter<student_assignment_adapter.ViewHolder> {
    TextView lectures;
    RecyclerView recyclerView;
    Context context;
    ArrayList<String> lecs=new ArrayList<>();// lectures by
    ArrayList<String> items= new ArrayList<>();// lecture names

    ArrayList<String> lecs_url=new ArrayList<>();// lecture urls
    ArrayList<String> lecs_date=new ArrayList<>();// lecture dates


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.assignments,parent,false);

        return new ViewHolder(view);
    }
    public void update(String name , String by, String date, String url )
    {
        items.add(name);
        lecs.add(by);
        lecs_url.add(url);
        lecs_date.add(date);
        notifyDataSetChanged();
    }

    public student_assignment_adapter(RecyclerView recyclerView, Context context, ArrayList<String> items, ArrayList<String> lecs,ArrayList<String> lecs_date,ArrayList<String> lecs_url) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.lecs=lecs;
        this.items = items;
        this.lecs_date=lecs_date;
        this.lecs_url=lecs_url;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lectures.setText(items.get(position));
        holder.date.setText(lecs_date.get(position));
        holder.by.setText(lecs.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lectures, by, date ;
        public ViewHolder(View itemView){
            super(itemView);
            lectures=itemView.findViewById(R.id.card_lecture);
            by=itemView.findViewById(R.id.card_lecture_by);
            date=itemView.findViewById(R.id.card_lecture_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=recyclerView.getChildLayoutPosition(v);
                    Intent i= new Intent();
                    i.setType(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(lecs_url.get(position)));
                    context.startActivity(i);
                }
            });
        }
    }
}
