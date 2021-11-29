package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Model.Komens;
import com.fitranda.qurbanku.databinding.ItemKomenBinding;

import java.util.List;

public class KomenAdapter extends RecyclerView.Adapter<KomenAdapter.ViewHolder> {
    private List<Komens> komenlist;
    Context context;

    public KomenAdapter(List<Komens> komenlist, Context context) {
        this.komenlist = komenlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKomenBinding binding = ItemKomenBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Komens kommen = komenlist.get(position);
        holder.bind.tvNamaPel.setText(kommen.getPelanggan());
        holder.bind.ratingBar3.setRating(Float.parseFloat(String.valueOf(kommen.getRating())));
        holder.bind.tvKomens.setText(kommen.getKomen());
        Glide.with(context).load(kommen.getGambar()).into(holder.bind.imageView3);
    }

    @Override
    public int getItemCount() {
        return komenlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemKomenBinding bind;
        public ViewHolder(@NonNull ItemKomenBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}
