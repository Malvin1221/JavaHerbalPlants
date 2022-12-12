package com.jhp.javaherbalplants;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterMinuman extends RecyclerView.Adapter<MyViewHolderMinuman> {

    private Context context;
    private List<DataClassMinuman> dataListMinuman;

    public MyAdapterMinuman(Context context, List<DataClassMinuman> dataListMinuman) {

        this.context = context;
        this.dataListMinuman = dataListMinuman;
    }
    @NonNull
    @Override
    public MyViewHolderMinuman onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerminuman_item, parent,false);
        return new MyViewHolderMinuman(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMinuman holder, int position) {
        Glide.with(context).load(dataListMinuman.get(position).getDataImageMinuman()).into(holder.recImageMinuman);
        holder.recTitleMinuman.setText(dataListMinuman.get(position).getDataTitleMinuman());
        holder.recDescMinuman.setText(dataListMinuman.get(position).getDataDescMinuman());
        holder.recLangMinuman.setText(dataListMinuman.get(position).getDataLangMinuman());

        holder.recCardMinuman.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivityMinuman.class);
                intent.putExtra("Image",dataListMinuman.get(holder.getAdapterPosition()).getDataImageMinuman());
                intent.putExtra("Description",dataListMinuman.get(holder.getAdapterPosition()).getDataDescMinuman());
                intent.putExtra("Title",dataListMinuman.get(holder.getAdapterPosition()).getDataTitleMinuman());
                intent.putExtra("Lang",dataListMinuman.get(holder.getAdapterPosition()).getDataLangMinuman());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){return dataListMinuman.size();}

    public void searchDataList(ArrayList<DataClassMinuman> searchList){
        dataListMinuman = searchList;
        notifyDataSetChanged();
    }
}
    class MyViewHolderMinuman extends RecyclerView.ViewHolder{
        ImageView recImageMinuman;
        TextView recTitleMinuman, recDescMinuman, recLangMinuman;
        CardView recCardMinuman;
        public MyViewHolderMinuman(@NonNull View itemView) {
            super(itemView);
            recImageMinuman = itemView.findViewById(R.id.recImageMinuman);
            recCardMinuman = itemView.findViewById(R.id.recCardMinuman);
            recDescMinuman = itemView.findViewById(R.id.recDescMinuman);
            recLangMinuman = itemView.findViewById(R.id.recLangMinuman);
            recTitleMinuman = itemView.findViewById(R.id.recTitleMinuman);
        }
    }
