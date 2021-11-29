package com.fitranda.qurbankupenjual.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbankupenjual.DetailPesanan;
import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.HistoriModel;
import com.fitranda.qurbankupenjual.Model.PesanM;
import com.fitranda.qurbankupenjual.databinding.BayaranBinding;
import com.fitranda.qurbankupenjual.databinding.ItemPBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;

public class PesanA extends RecyclerView.Adapter<PesanA.ViewHolder>{
    List<PesanM> list;
    Context context;

    public PesanA(List<PesanM> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPBinding binding = ItemPBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PesanM p = list.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfw = new SimpleDateFormat("HH:mm:ss");

        Date now = new Date();
        String tgl = sdf.format(now);
        String waktu =sdfw.format(now);

        holder.bind.tvJudulhistori.setText(p.getPelanggan());
        holder.bind.tglhistori.setText(p.getTanggal() + " " + p.getWaktu());
        holder.bind.alamat.setText(p.getAlamattujuan());
        Glide.with(context).load(p.getGambar()).into(holder.bind.imageView2);
        if (p.getFlagkirim() == 0){
            holder.bind.tvItemhistori.setText("Kirim ?");

            holder.bind.tvItemhistori.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "kirim", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("PERINGATAN");
                    builder.setMessage("Yakin anda mengirim");
                    builder.setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new EasyWebservice(API.url+"orderf/"+p.getNotransaksi())
                                    .method(Method.POST)
                                    .addParam("flagkirim",1)
                                    .call(new Callback.A<String>("pesan") {
                                        @Override
                                        public void onSuccess(String s) {
                                            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onError(String s) {
                                            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            new EasyWebservice(API.url+"notifp")
                                    .addParam("idpenjual",p.getIdpenjual())
                                    .addParam("idpelanggan",p.getIdpelanggan())
                                    .addParam("notif","Pesanan pada tanggal "+ p.getTanggal() +" sedang dikirim")
                                    .addParam("tanggal",tgl)
                                    .addParam("waktu",waktu)
                                    .call(new hivatec.ir.easywebservice.Callback.A<String>("pesan") {
                                        @Override
                                        public void onSuccess(String s) {
                                            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onError(String s) {
                                            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();

                }
            });
        }else if (p.getFlagkirim() == 1){
            holder.bind.tvItemhistori.setText("Bayar");
            holder.bind.tvItemhistori.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "bayar", Toast.LENGTH_SHORT).show();
                    BayaranBinding binds = BayaranBinding.inflate(LayoutInflater.from(context));
                    AlertDialog dialog = new AlertDialog.Builder(context).create();
                    dialog.setView(binds.getRoot());
                    dialog.setCancelable(true);
                    dialog.show();
                    binds.etAlamat.setText(p.getTotal());

                    binds.btnBatal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    binds.btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("PERINGATAN");
                            builder.setMessage("Yakin anda mengirim");
                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new EasyWebservice(API.url+"order/"+p.getNotransaksi())
                                            .method(Method.POST)
                                            .addParam("bayar",p.getTotal())
                                            .addParam("kembali",0)
                                            .call(new Callback.A<String>("pesan") {
                                                @Override
                                                public void onSuccess(String s) {
                                                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onError(String s) {
                                                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                    new EasyWebservice(API.url+"orderf/"+p.getNotransaksi())
                                            .method(Method.POST)
                                            .addParam("flagkirim",2)
                                            .call(new Callback.A<String>("pesan") {
                                                @Override
                                                public void onSuccess(String s) {
                                                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onError(String s) {
                                                    Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    notifyDataSetChanged();
                                }
                            });

                            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();

                            notifyDataSetChanged();


                        }
                    });
                }
            });

        }
//        if (p.status){
//            holder.bind.tvItemhistori.setVisibility(View.VISIBLE);
//
//        }else {
//            holder.bind.tvItemhistori.setVisibility(View.GONE);
//            holder.bind.clHistori.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    context.startActivity(new Intent(context, DetailPesanan.class));
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPBinding bind;
        public ViewHolder(@NonNull ItemPBinding itemView) {
            super(itemView.getRoot());
            bind = itemView;
        }
    }
}
