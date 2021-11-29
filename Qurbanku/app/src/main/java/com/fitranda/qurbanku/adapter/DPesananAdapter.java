package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Model.DPesanM;
import com.fitranda.qurbanku.Model.KeranjangModel;
import com.fitranda.qurbanku.databinding.ItemDetailPesananBinding;

import java.util.List;

public class DPesananAdapter extends RecyclerView.Adapter<DPesananAdapter.ViewHolder> {
    Context context;
    List<DPesanM> list;

    public DPesananAdapter(Context context, List<DPesanM> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailPesananBinding bind = ItemDetailPesananBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new ViewHolder(bind);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DPesanM kr = list.get(position);
        holder.binding.tvJudulhistori.setText(kr.getHewan());
        holder.binding.tvHarga.setText(kr.getHargajual());
        Glide.with(context).load(kr.getGambar()).into(holder.binding.ivToko);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemDetailPesananBinding binding;
        public ViewHolder(@NonNull ItemDetailPesananBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
