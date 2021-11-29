package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Model.DPesanM;
import com.fitranda.qurbanku.databinding.ItemDetailPesananBinding;

import java.util.List;

public class DPA extends RecyclerView.Adapter<DPA.ViewHolder> {
    Context context;
    List<DPesanM> data;

    public DPA(Context context, List<DPesanM> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailPesananBinding binding = ItemDetailPesananBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DPesanM dpm = data.get(position);
        holder.bind.tvJudulhistori.setText(dpm.getHewan());
        holder.bind.tvHarga.setText(dpm.getHargajual().toString());
        Glide.with(context).load(dpm.getGambar()).into(holder.bind.ivToko);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemDetailPesananBinding bind;
        public ViewHolder(@NonNull ItemDetailPesananBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}
