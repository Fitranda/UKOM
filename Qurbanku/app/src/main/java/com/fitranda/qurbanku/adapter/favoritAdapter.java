package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Detail_Activity_Toko;
import com.fitranda.qurbanku.Model.Toko;
import com.fitranda.qurbanku.Model.mFavorit;
import com.fitranda.qurbanku.databinding.ItemCardTokoBinding;

import java.util.List;

public class favoritAdapter extends RecyclerView.Adapter<favoritAdapter.ViewHolder> {
    private Context context;
    private List<mFavorit> data;

    public favoritAdapter(Context context, List<mFavorit> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardTokoBinding binding = ItemCardTokoBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mFavorit toko = data.get(position);
        holder.bind.tvJudulhistori.setText(toko.getPenjual());
        holder.bind.tvAlamat.setText(toko.getAlamat());
        holder.bind.ratingBar.setRating(Float.parseFloat(String.valueOf(toko.getRatings())));
        Glide.with(context).load(toko.getGambar()).into(holder.bind.ivToko);
        holder.bind.tokos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Detail_Activity_Toko.class);
                i.putExtra("toko",toko.getPenjual());
                i.putExtra("gambar",toko.getGambar());
                i.putExtra("alamat",toko.getAlamat());
                i.putExtra("link",toko.getLink());
                i.putExtra("telp",toko.getTelp());
                i.putExtra("ratings",String.valueOf(toko.getRatings()));
                i.putExtra("id",toko.getIdpenjual());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardTokoBinding bind;
        public ViewHolder(@NonNull ItemCardTokoBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}
