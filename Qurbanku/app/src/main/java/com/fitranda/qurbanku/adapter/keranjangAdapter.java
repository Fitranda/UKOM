package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Detail_Item;
import com.fitranda.qurbanku.Help;
import com.fitranda.qurbanku.Helper.Database;
import com.fitranda.qurbanku.Keranjang;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.KeranjangModel;
import com.fitranda.qurbanku.R;
import com.fitranda.qurbanku.databinding.ItemKeranjangBinding;

import java.util.ArrayList;
import java.util.List;

public class keranjangAdapter extends RecyclerView.Adapter<keranjangAdapter.ViewHolder> {
    Context context;
    List<KeranjangModel> krlist;
    private Boolean status;
    Database db;

    public keranjangAdapter(Context context, List<KeranjangModel> krlist, Boolean status) {
        this.context = context;
        this.krlist = krlist;
        this.status = status;
        db = new Database(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemKeranjangBinding binding = ItemKeranjangBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KeranjangModel kr = krlist.get(position);
        holder.bind.ivToko.setImageResource(R.drawable.kambing);
        holder.bind.tvJudulhistori.setText(kr.getHewan());
        holder.bind.tvHarga.setText(kr.getHarga());
        Glide.with(context).load(kr.getGambar()).into(holder.bind.ivToko);
        holder.bind.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("PERINGATAN");
                builder.setMessage("Yakin akan menghapus");
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String SQL = "DELETE FROM tblkeranjang WHERE hewan ='"+kr.getHewan()+"' and harga = "+ kr.getHarga() +";";
                        if (db.runSQL(SQL)){
                            krlist.remove(position);

                            String sql1 = "SELECT * FROM tblkeranjang order by harga ASC";
                            Cursor cursor = db.select(sql1);
                            if (cursor.getCount() > 0){
                                notifyItemRemoved(position);
                            }else{
                                ((Keranjang)context).Peringatan("Data Kosong");
                                ((Keranjang)context).setTotal("0");
                                notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });
//        holder.bind.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, Detail_Item.class);
//                i.putExtra("BT",status);
//                i.putExtra("hewan",hewan.getHewan());
//                i.putExtra("harga",hewan.getHarga());
//                i.putExtra("toko",hewan.getToko());
//                i.putExtra("alamat",hewan.getAlamat());
//                context.startActivity(i);
//                Help dt = new Help();
//                dt.s = status;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return krlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemKeranjangBinding bind;
        public ViewHolder(@NonNull ItemKeranjangBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}
