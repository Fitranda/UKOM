package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Detail_Pesanan;
import com.fitranda.qurbanku.Model.PesanM;
import com.fitranda.qurbanku.databinding.ItemPesanBinding;

import java.util.List;

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.ViewHolder> {
    Context context;
    List<PesanM> list;

    public PesanAdapter(Context context, List<PesanM> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPesanBinding bind = ItemPesanBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PesanM pesan = list.get(position);
        holder.binding.tvJudulhistori.setText(pesan.getPenjual());
        holder.binding.tglhistori.setText(pesan.getTanggal());
        holder.binding.tvNo.setText(pesan.getNotransaksi());
        if (pesan.getFlagkirim() == 0){
            holder.binding.tvStatus.setText("Menunggu Konfirmasi");
        }else if (pesan.getFlagkirim() == 1){
            holder.binding.tvStatus.setText("Pengiriman");
        }
        Glide.with(context).load(pesan.getGambar()).into(holder.binding.imageView2);


        holder.binding.clHistori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Detail_Pesanan.class);
                i.putExtra("no",pesan.getNotransaksi().toString());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPesanBinding binding;
        public ViewHolder(@NonNull ItemPesanBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
