package com.semester4.utsmcs;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList itemID, itemName, addDate, itemCost;
    int position;

    RecyclerAdapter(MainActivity mainActivity, Context ctx, ArrayList itemID,
                    ArrayList itemName, ArrayList addDate, ArrayList itemCost) {
        this.ctx = ctx;
        this.itemID = itemID;
        this.itemName = itemName;
        this.addDate = addDate;
        this.itemCost = itemCost;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.item_list, parent, false);
//        ctx = parent.getContext();
//        View view = LayoutInflater.from(ctx).inflate(
//                R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        this.position = position;
        holder.idHide.setText(String.valueOf(itemID.get(position)));
        holder.nameDisplay.setText(String.valueOf(itemName.get(position)));
        holder.dateDisplay.setText(String.valueOf(addDate.get(position)));
        holder.costDisplay.setText(String.valueOf(itemCost.get(position)));

        holder.displayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, UpdateItem.class);
                intent.putExtra("id", String.valueOf(itemID.get(position)));
                intent.putExtra("name", String.valueOf(itemName.get(position)));
                intent.putExtra("date", String.valueOf(addDate.get(position)));
                intent.putExtra("cost", String.valueOf(itemCost.get(position)));
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemID.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameDisplay, dateDisplay, costDisplay, idHide;
        FrameLayout displayLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idHide = itemView.findViewById(R.id.id_hide);
            nameDisplay = itemView.findViewById(R.id.item_name_display);
            dateDisplay = itemView.findViewById(R.id.date_display);
            costDisplay = itemView.findViewById(R.id.item_cost_display);
            displayLayout = itemView.findViewById(R.id.displayLayout);
        }
    }
}