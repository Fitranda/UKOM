package com.fitranda.qurbankupenjual.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbankupenjual.DetailPesanan;
import com.fitranda.qurbankupenjual.Model.HistoriModel;
import com.fitranda.qurbankupenjual.databinding.ItemHistoriBinding;

import java.util.List;

public class HistoriAdapter extends RecyclerView.Adapter<HistoriAdapter.ViewHolder> {
    List<HistoriModel> historilist;
    Context context;
    Boolean status = true;

    public HistoriAdapter(List<HistoriModel> historilist, Context context, Boolean status) {
        this.historilist = historilist;
        this.context = context;
        this.status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoriBinding binding = ItemHistoriBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoriModel histori = historilist.get(position);

        holder.bind.tvJudulhistori.setText(histori.getPelanggan());
        holder.bind.tglhistori.setText(histori.getTanggal() + " " + histori.getWaktu());
        Glide.with(context).load(histori.getGambar()).into(holder.bind.imageView2);
//        holder.bind.clHistori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Boolean AdaBayar;
//                if (status){
//                    AdaBayar = true;
//                }else{
//                    AdaBayar = false;
//                }
//                Intent i = new Intent(context, DetailPesanan.class);
//                i.putExtra("ada",AdaBayar);
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return historilist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemHistoriBinding bind;

        public ViewHolder(@NonNull ItemHistoriBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}
