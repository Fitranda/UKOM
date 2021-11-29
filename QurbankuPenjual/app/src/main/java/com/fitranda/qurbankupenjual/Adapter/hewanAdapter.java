package com.fitranda.qurbankupenjual.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.MainActivity;
import com.fitranda.qurbankupenjual.Model.Hewan;
import com.fitranda.qurbankupenjual.Model.PesanOnly;
import com.fitranda.qurbankupenjual.databinding.ItemCardHewanBinding;
import com.fitranda.qurbankupenjual.ui.home.HomeFragment;

import java.util.List;

import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hewanAdapter extends RecyclerView.Adapter<hewanAdapter.ViewHolder>{
    List<Hewan> data;
    Context context;
    Boolean Status = true;

    public hewanAdapter(List<Hewan> data, Context context, Boolean status) {
        this.data = data;
        this.context = context;
        Status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardHewanBinding binding = ItemCardHewanBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hewan hewan = data.get(position);
        holder.bind.tvJudulhistori.setText(hewan.getHewan());
        holder.bind.tvHarga.setText(hewan.getHarga().toString());
        Glide.with(context).load(hewan.getGambar()).into(holder.bind.ivToko);

        holder.bind.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                if (Status){
//            holder.bind.btnHapus.setVisibility(View.VISIBLE);
                    text = "Yakin akan menghapus?";
                }else{
//            holder.bind.btnHapus.setVisibility(View.GONE);
                    text = "Apakah hewan tidak dapat dikirim?";
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("PERINGATAN");
                builder.setMessage(text);
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        new EasyWebservice(API.url+"hewan/"+hewan.getIdhewan())
//                                .method(Method.DELETE)
//                                .call(new Callback.A<String>("pesan") {
//
//                                    @Override
//                                    public void onSuccess(String s) {
//                                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//                                    }
//
//                                    @Override
//                                    public void onError(String s) {
//                                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            data.remove(position);
//                            notifyItemRemoved(position);
                        Call<PesanOnly> call = API.getService().hapusHewan(hewan.getIdhewan());
                        Log.d("url",call.request().url().toString());
                        call.enqueue(new Callback<PesanOnly>() {
                            @Override
                            public void onResponse(Call<PesanOnly> call, Response<PesanOnly> response) {
                                Toast.makeText(context, response.body().pesan, Toast.LENGTH_SHORT).show();
//                                MainActivity hf = new MainActivity();
//                                hf.refresfgj();
                            }

                            @Override
                            public void onFailure(Call<PesanOnly> call, Throwable t) {

                            }
                        });

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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCardHewanBinding bind;
        public ViewHolder(@NonNull ItemCardHewanBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}