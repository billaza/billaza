package com.semester4.uasmcsnabilla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {

    private Context ctx;
    private ArrayList id, word;
    private OnItemClickListener mListener;
    int position;

    RecyclerAdapter2(Favorites favorites, Context ctx, ArrayList id, ArrayList word) {
        this.ctx = ctx;
        this.id = id;
        this.word = word;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onSaveClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.favorite_list, parent, false);
        RecyclerAdapter2.ViewHolder ravh = new RecyclerAdapter2.ViewHolder(v, mListener);
        return ravh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter2.ViewHolder holder, int position) {
        this.position = position;
        holder.words.setText(String.valueOf(word.get(position)));
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView words;
        Button delete;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            words = itemView.findViewById(R.id.saved_words);
            layout = itemView.findViewById(R.id.favorite_layout);
            delete = itemView.findViewById(R.id.delete_button);

            // click for preview
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            // click delete
            itemView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onSaveClick(position);
                        }
                    }
                }
            });
        }
    }
}