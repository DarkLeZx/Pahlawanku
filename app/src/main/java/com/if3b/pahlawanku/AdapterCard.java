package com.if3b.pahlawanku;

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

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.ClassViewHolder>
{
    private ArrayList<ModelPahlawan> dataPahlawan;

    public AdapterCard(ArrayList<ModelPahlawan> dataPahlawan)
    {
        this.dataPahlawan = dataPahlawan;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent,false);
        return new ClassViewHolder(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ModelPahlawan Pahlawan = dataPahlawan.get(position);
        holder.tvNama.setText(Pahlawan.getNama());
        holder.tvTentang.setText(Pahlawan.getTentang());
        Glide
                .with(holder.itemView.getContext()).
                load(Pahlawan.getFoto())
                .centerCrop()
                .into(holder.ivFoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String xNama, xTentang, xFoto;
            xNama = Pahlawan.getNama();
            xTentang = Pahlawan.getTentang();
            xFoto = Pahlawan.getFoto();

//                Log.d("Pengecekan Data", xNama + " | " + xTentang + " | " + xFoto);

                Intent kirim = new Intent(holder.itemView.getContext(), DetailActivity.class);
                kirim.putExtra("xNama", xNama);
                kirim.putExtra("xTentang", xTentang);
                kirim.putExtra("xFoto", xFoto);

                holder.itemView.getContext().startActivity(kirim);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPahlawan.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivFoto;
        TextView tvNama, tvTentang;
        public ClassViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvTentang = itemView.findViewById(R.id.tv_tentang);
        }


    }
}
