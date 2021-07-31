package com.semester4.uasmcsnabilla;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context ctx;
    private List<WordResult> wordResultList;
    private OnItemClickListener mListener;
    int position;

    RecyclerAdapter(MainActivity mainActivity, Context ctx) {
        this.ctx = ctx;
//        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onSaveClick(int position);
    }

    public void setData(List<WordResult> wordResultList) {
        this.wordResultList = wordResultList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.explore_list, parent, false);
        RecyclerAdapter.ViewHolder ravh = new RecyclerAdapter.ViewHolder(v, mListener);
        return ravh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        WordResult wordResult = wordResultList.get(position);
        String word = wordResult.getWord();
        holder.words.setText(word);
    }

    @Override
    public int getItemCount() {
        return wordResultList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView words;
        Button save;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            words = itemView.findViewById(R.id.words);
            layout = itemView.findViewById(R.id.explore_layout);
            save = itemView.findViewById(R.id.save_button);

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

            // click save
            itemView.findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
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