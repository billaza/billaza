package com.semester4.uasmcsnabilla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.ViewHolder> {

    private Context ctx;
    private List<WordResult> wordResultList;
    int position;

    RecyclerAdapter3(Preview preview, Context ctx) {
        this.ctx = ctx;
    }

    public void setData(List<WordResult> wordResultList) {
        this.wordResultList = wordResultList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(
                R.layout.preview_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter3.ViewHolder holder, int position) {

        WordResult wordResult = wordResultList.get(position);

        String type = wordResult.getDefinitions().get(position).getType();
        String url = wordResult.getDefinitions().get(position).getImageUrl();
        String def = wordResult.getDefinitions().get(position).getDefinition();

        holder.type.setText(type);
        holder.def.setText(def);
        Picasso.with(ctx).load(url).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return wordResultList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, def;
        ImageView image;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.word_type);
            layout = itemView.findViewById(R.id.definition_layout);
            def = itemView.findViewById(R.id.definition_text);
            image = itemView.findViewById(R.id.word_image);
            image.getLayoutParams().height = 400;
        }
    }
}