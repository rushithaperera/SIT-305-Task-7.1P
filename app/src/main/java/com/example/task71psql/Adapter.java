package com.example.task71psql;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    Activity activity;
    List<Model> noteList;

    public Adapter(Context context, Activity activity, List<Model> noteList) {
        this.context = context;
        this.activity = activity;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(noteList.get(position).getTitle());
        holder.description.setText(noteList.get(position).getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateNote.class);

                intent.putExtra("title", noteList.get(position).getTitle());
                intent.putExtra("description", noteList.get(position).getDescription());
                intent.putExtra("id", noteList.get(position).getId());

                activity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        RelativeLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            layout = itemView.findViewById(R.id.note_layout);
        }
    }
}
