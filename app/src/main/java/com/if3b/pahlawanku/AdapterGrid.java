package com.if3b.pahlawanku;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ClassViewHolder>
{
    private ArrayList<ModelPahlawan> dataPahlawan;
    private Context ctx;
    public AdapterGrid(ArrayList<ModelPahlawan> dataPahlawan, Context ctx)
    {
        this.dataPahlawan = dataPahlawan;
        this.ctx = ctx ;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.item_grid, parent,false);
        return new ClassViewHolder(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ModelPahlawan Pahlawan = dataPahlawan.get(position);
        Glide
                .with(ctx).
                load(Pahlawan.getFoto())
                .centerCrop()
                .into(holder.ivGrid);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xNama, xTentang, xFoto;
                xNama = Pahlawan.getNama();
                xTentang = Pahlawan.getTentang();
                xFoto = Pahlawan.getFoto();

//                Log.d("Pengecekan Data", xNama + " | " + xTentang + " | " + xFoto);

                Intent kirim = new Intent(ctx, DetailActivity.class);
                kirim.putExtra("xNama", xNama);
                kirim.putExtra("xTentang", xTentang);
                kirim.putExtra("xFoto", xFoto);
                ctx.startActivity(kirim);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPahlawan.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivGrid;
        public ClassViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivGrid = itemView.findViewById(R.id.iv_Grid);

        }


    }
}
