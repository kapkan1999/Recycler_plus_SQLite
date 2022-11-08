package com.example.recycler_plus_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    // Да, нельзя здесь точно сохранять контекст
    // И поправь все замечания от студии, которые она подствечивает, обычно хуйни не советует
    private Context context;
    //Везде принят camelCase
    private ArrayList name_id, email_id, age_id;

    public MyAdapter(Context context, ArrayList name_id, ArrayList email_id, ArrayList age_id) {
        this.context = context;
        this.name_id = name_id;
        this.email_id = email_id;
        this.age_id = age_id;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_entry, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        // Обычно не так делают, сделай публичный метод у MyViewHolder - bind('параметры'),
        // и передай в него все, что нужно, там же обнови view
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.email_id.setText(String.valueOf(email_id.get(position)));
        holder.age_id.setText(String.valueOf(age_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    //Название лучше поменять, это ни о чем не говорит
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name_id, email_id, age_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            email_id = itemView.findViewById(R.id.textemail);
            age_id = itemView.findViewById(R.id.textage);
        }
    }
}
