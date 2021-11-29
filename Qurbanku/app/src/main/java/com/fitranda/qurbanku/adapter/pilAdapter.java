package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitranda.qurbanku.Helper.Database;
import com.fitranda.qurbanku.Keranjang;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.KeranjangModel;
import com.fitranda.qurbanku.Model.Toko;
import com.fitranda.qurbanku.Model.pilModel;
import com.fitranda.qurbanku.Pencarian;
import com.fitranda.qurbanku.databinding.ItemKomenBinding;
import com.fitranda.qurbanku.databinding.ItemPilBinding;

import java.util.ArrayList;
import java.util.List;

public class pilAdapter extends RecyclerView.Adapter<pilAdapter.ViewHolder>{
    private List<pilModel> pils;
    keranjangAdapter adapter;
    List<KeranjangModel> hewanList;
    Database db;
    Context contex;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    public pilAdapter(List<pilModel> pils, Context contex) {
        this.pils = pils;
        this.contex = contex;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPilBinding binding = ItemPilBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new pilAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pilModel pil = pils.get(position);
        holder.bind.tvPil.setText(pil.pil);

        db = new Database(contex);
        String sql = "SELECT * FROM tblkeranjang where idpenjual = "+ pil.id;
        Cursor cursor = db.select(sql);

        if (cursor== null) {
            Toast.makeText(contex, sql, Toast.LENGTH_SHORT).show();
            Log.d("sql",sql);
        }else{
            holder.bind.listCari.setLayoutManager(new LinearLayoutManager(contex));
//            Log.d("null",String.valueOf(cursor.isNull(0)));
//            Log.d("afterlast",String.valueOf(cursor.isAfterLast()));
//            Log.d("closed",String.valueOf(cursor.isClosed()));
//            Log.d("before",String.valueOf(cursor.isBeforeFirst()));
//            Log.d("first",String.valueOf(cursor.isFirst()));
//            Log.d("last",String.valueOf(cursor.isLast()));

            hewanList = new ArrayList<KeranjangModel>();
            if (cursor.getCount() > 0){
                if (cursor.moveToFirst()){
                    do {
                        String hewan = cursor.getString(cursor.getColumnIndex("hewan"));
                        String harga = cursor.getString(cursor.getColumnIndex("harga"));
                        String toko = cursor.getString(cursor.getColumnIndex("toko"));
                        String alamat = cursor.getString(cursor.getColumnIndex("alamat"));
                        String gambar = cursor.getString(cursor.getColumnIndex("gambar"));
                        hewanList.add(new KeranjangModel(hewan,harga,gambar));
                    }while (cursor.moveToNext());
                }
                adapter = new keranjangAdapter(contex,hewanList,false);
                holder.bind.listCari.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else{

//                Peringatan("Data Kosong");
//                bind.button3.setVisibility(View.GONE);
            }


            Cursor c = db.select("SELECT SUM(harga) FROM tblkeranjang where idpenjual=" + pil.id);

            if (c.moveToNext()){
                holder.bind.tvTotals.setText(c.getString(0));
            }else{
                holder.bind.tvTotals.setText(0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return pils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPilBinding bind;
        public ViewHolder(@NonNull ItemPilBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}
