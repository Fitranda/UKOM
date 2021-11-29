package com.fitranda.qurbanku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Helper.Database;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.HistoriModel;
import com.fitranda.qurbanku.Model.KeranjangModel;
import com.fitranda.qurbanku.Model.Order;
import com.fitranda.qurbanku.Model.pilModel;
import com.fitranda.qurbanku.adapter.hewanAdapter;
import com.fitranda.qurbanku.adapter.historiAdapter;
import com.fitranda.qurbanku.adapter.keranjangAdapter;
import com.fitranda.qurbanku.adapter.pilAdapter;
import com.fitranda.qurbanku.databinding.ActivityKeranjangBinding;
import com.fitranda.qurbanku.databinding.FixAlamatBinding;
import com.jakewharton.processphoenix.ProcessPhoenix;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Keranjang extends AppCompatActivity {

    keranjangAdapter adapter;
    List<KeranjangModel> hewanList;
    List<pilModel> list;
    Database db;
    ActivityKeranjangBinding bind;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityKeranjangBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.button3.setVisibility(View.VISIBLE);
        db = new Database(Keranjang.this);
        db.buatTabel();
        initView();
        isiData();

        Cursor c = db.select("SELECT SUM(harga) FROM tblkeranjang");

        if (c == null){
            bind.tvTotal.setText(0);
        }else {
            if (c.moveToNext()){
                bind.tvTotal.setText(c.getString(0));
            }else{
                bind.tvTotal.setText(0);
            }
        }
        bind.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FixAlamatBinding binds = FixAlamatBinding.inflate(LayoutInflater.from(Keranjang.this));
                AlertDialog dialog = new AlertDialog.Builder(Keranjang.this).create();
                dialog.setView(binds.getRoot());
                dialog.setCancelable(true);
                dialog.show();

                binds.btnBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                binds.btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String al = binds.etAlamat.getText().toString();

//                        Toast.makeText(Keranjang.this, al, Toast.LENGTH_SHORT).show();

                        if (al.length() == 0){
                            Toast.makeText(Keranjang.this, "Isi semua", Toast.LENGTH_SHORT).show();
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Keranjang.this);
                            builder.setTitle("PERINGATAN");
                            builder.setMessage("Yakin anda memesan");
                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    String SQLP = "SELECT * FROM tblkeranjang group by idpenjual order by harga ASC";

                                    Cursor cursors = db.select(SQLP);

                                    if (cursors== null) {
                                        Log.d("ue",SQLP);
                                        Toast.makeText(Keranjang.this, SQLP, Toast.LENGTH_SHORT).show();
                                    }else{

                                        if (cursors.getCount() > 0){
                                            if (cursors.moveToFirst()){
                                                do {
                                                    Integer id = Integer.parseInt(cursors.getString(cursors.getColumnIndex("idpenjual")));
                                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                    SimpleDateFormat sdfw = new SimpleDateFormat("HH:mm:ss");

                                                    Date now = new Date();
                                                    String tgl = sdf.format(now);
                                                    String waktu =sdfw.format(now);

                                                    pref = getSharedPreferences(session,MODE_PRIVATE);
                                                    Integer idpelanggan = pref.getInt("idpelanggan",0);

                                                    Order order = new Order(tgl+"-"+id+"-"+waktu+"-"+idpelanggan,al,tgl,waktu,id,idpelanggan,Integer.parseInt(bind.tvTotal.getText().toString()),0);

                                                    Call<Order> call = API.getService().ordering(order);
                                                    call.enqueue(new Callback<Order>() {
                                                        @Override
                                                        public void onResponse(Call<Order> call, Response<Order> response) {
//                                            Toast.makeText(Keranjang.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Order> call, Throwable t) {
//                                            Toast.makeText(Keranjang.this, "gagal", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                    db = new Database(Keranjang.this);
                                                    String sql = "SELECT * FROM tblkeranjang where idpenjual = "+id;
                                                    Cursor cursor = db.select(sql);
                                                    if (cursor.getCount() > 0){
                                                        if (cursor.moveToFirst()){
                                                            do {
                                                                new EasyWebservice(API.url+"detail")
                                                                        .method(Method.POST)
                                                                        .addParam("notransaksi",tgl+"-"+id+"-"+waktu+"-"+idpelanggan)
                                                                        .addParam("idhewan",cursor.getString(cursor.getColumnIndex("idhewan")))
                                                                        .addParam("hargajual",cursor.getString(cursor.getColumnIndex("harga")))
                                                                        .addParam("flagada",1)
                                                                        .call(new hivatec.ir.easywebservice.Callback.A<String>("pesan") {
                                                                            @Override
                                                                            public void onSuccess(String s) {
                                                                                Toast.makeText(Keranjang.this, s, Toast.LENGTH_SHORT).show();
                                                                            }

                                                                            @Override
                                                                            public void onError(String s) {
                                                                                Toast.makeText(Keranjang.this, s, Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });

                                                                new EasyWebservice(API.url+"notift")
                                                                        .addParam("idpenjual",id)
                                                                        .addParam("idpelanggan",idpelanggan)
                                                                        .addParam("notif",cursor.getString(cursor.getColumnIndex("hewan"))+"\n anda dipesan")
                                                                        .addParam("tanggal",tgl)
                                                                        .addParam("waktu",waktu)
                                                                        .call(new hivatec.ir.easywebservice.Callback.A<String>("pesan") {
                                                                            @Override
                                                                            public void onSuccess(String s) {
                                                                                Toast.makeText(Keranjang.this, s, Toast.LENGTH_SHORT).show();
                                                                            }

                                                                            @Override
                                                                            public void onError(String s) {
                                                                                Toast.makeText(Keranjang.this, s, Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });
                                                            }while (cursor.moveToNext());
                                                        }
                                                    }
                                                }while (cursors.moveToNext());
                                            }
                                        }

                                    }





                                    if (db.runSQL("DELETE FROM tblkeranjang")){
                                        finish();

                                        Intent i = new Intent(Keranjang.this,Pesanan.class);
//                                    ProcessPhoenix.triggerRebirth(Keranjang.this,i);s
                                        startActivity(i);
                                    }else{
                                        Toast.makeText(Keranjang.this, "gagal", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }


                    }
                });
            }
        });
    }

    private void isiData() {
        db = new Database(Keranjang.this);
        String sql = "SELECT * FROM tblkeranjang group by idpenjual order by harga ASC";
        Cursor cursor = db.select(sql);

        if (cursor== null) {
            Peringatan("Data Kosong");
            bind.button3.setVisibility(View.GONE);
        }else{
//            Log.d("null",String.valueOf(cursor.isNull(0)));
//            Log.d("afterlast",String.valueOf(cursor.isAfterLast()));
//            Log.d("closed",String.valueOf(cursor.isClosed()));
//            Log.d("before",String.valueOf(cursor.isBeforeFirst()));
//            Log.d("first",String.valueOf(cursor.isFirst()));
//            Log.d("last",String.valueOf(cursor.isLast()));

            hewanList = new ArrayList<KeranjangModel>();
            list = new ArrayList<pilModel>();
            if (cursor.getCount() > 0){
                if (cursor.moveToFirst()){
                    do {
                        String hewan = cursor.getString(cursor.getColumnIndex("hewan"));
                        String harga = cursor.getString(cursor.getColumnIndex("harga"));
                        String toko = cursor.getString(cursor.getColumnIndex("toko"));
                        String alamat = cursor.getString(cursor.getColumnIndex("alamat"));
                        String gambar = cursor.getString(cursor.getColumnIndex("gambar"));
                        Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("idpenjual")));
//                        hewanList.add(new KeranjangModel(hewan,harga,gambar));
                        list.add(new pilModel(toko,id));
                    }while (cursor.moveToNext());
                }
//                adapter = new keranjangAdapter(Keranjang.this,hewanList,false);
//                bind.listKeranjang.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
                bind.listKeranjang.setAdapter(new pilAdapter(list,Keranjang.this));
            }else{

                Peringatan("Data Kosong");
                bind.button3.setVisibility(View.GONE);
            }


            Cursor c = db.select("SELECT SUM(harga) FROM tblkeranjang");

            if (c.moveToNext()){
                bind.tvTotal.setText(c.getString(0));
            }else{
                bind.tvTotal.setText(0);
            }
        }

    }

    public void setTotal(String total){
        bind.tvTotal.setText(total);
        bind.button3.setVisibility(View.GONE);
    }

    public void Peringatan(String pesan){
        AlertDialog al = new AlertDialog.Builder(this).create();
        al.setTitle("Peringatan!");
        al.setIcon(R.drawable.ic_error);
        al.setMessage(pesan);
        al.show();
    }

    private void initView() {
//        rcv = (RecyclerView) findViewById(R.id.listKeranjang);
        bind.listKeranjang.setLayoutManager(new LinearLayoutManager(this));
    }
}