package com.fitranda.qurbanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.Komens;
import com.fitranda.qurbanku.Model.mFavorit;
import com.fitranda.qurbanku.adapter.KomenAdapter;
import com.fitranda.qurbanku.adapter.hewanAdapter;
import com.fitranda.qurbanku.databinding.ActivityDetailTokoBinding;

import java.util.ArrayList;
import java.util.List;

import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Activity_Toko extends AppCompatActivity {
    ActivityDetailTokoBinding bind;
    List<Komens> data;
    List<Hewan> dataHewan;
    List<mFavorit> listf;
    Integer bol,idfavorit;
    boolean ada = false;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailTokoBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        pref = Detail_Activity_Toko.this.getSharedPreferences(session,MODE_PRIVATE);
        Integer idpelanggan = pref.getInt("idpelanggan",0);

        Glide.with(this).load(getIntent().getStringExtra("gambar")).into(bind.imageView);
        bind.tvNama.setText(getIntent().getStringExtra("toko"));
        bind.tvAlamat.setText(getIntent().getStringExtra("alamat"));
        bind.ratingBar2.setRating(Float.parseFloat(getIntent().getStringExtra("ratings")));
        bind.tvLink.setText(getIntent().getStringExtra("link"));
        bind.tvWA.setText(getIntent().getStringExtra("telp"));
        bind.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bol == 1){
                        new EasyWebservice(API.url+"favorit/"+idfavorit)
                                .method(Method.POST)
                                .addParam("like",0)
                                .call(new hivatec.ir.easywebservice.Callback.A<String>("pesan") {
                                    @Override
                                    public void onSuccess(String s) {
                                        bind.btnLike.setBackground(getDrawable(R.drawable.borders));
                                        bind.btnLike.setTextColor(Color.BLACK);
                                        bol = 0;
                                    }

                                    @Override
                                    public void onError(String s) {
                                        Toast.makeText(Detail_Activity_Toko.this, s, Toast.LENGTH_SHORT).show();
                                    }
                                });


                }else{
                    bol =1;
                    Log.d("url ins",API.url+"favorit");
                    if (ada){
                        new EasyWebservice(API.url+"favorit/"+idfavorit)
                                .method(Method.POST)
                                .addParam("like",1)
                                .call(new hivatec.ir.easywebservice.Callback.A<String>("pesan") {
                                    @Override
                                    public void onSuccess(String s) {
                                        bind.btnLike.setBackground(getDrawable(R.color.red));
                                        bind.btnLike.setTextColor(Color.WHITE);
                                        bol =1;
                                    }

                                    @Override
                                    public void onError(String s) {
                                        Toast.makeText(Detail_Activity_Toko.this, s, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else {
                        new EasyWebservice(API.url+"favorit")
                                .method(Method.POST)
                                .addParam("idpelanggan",idpelanggan)
                                .addParam("idpenjual",getIntent().getIntExtra("id",0))
                                .addParam("like",1)
                                .call(new hivatec.ir.easywebservice.Callback.A<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        bind.btnLike.setBackground(getDrawable(R.color.red));
                                        bind.btnLike.setTextColor(Color.WHITE);
                                        bol =1;
                                    }

                                    @Override
                                    public void onError(String s) {
                                        Toast.makeText(Detail_Activity_Toko.this, s, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                }
            }
        });


        init();
    }

    void init() {
        dummyData();
        setData(data,dataHewan);
    }

    void dummyData(){
        pref = Detail_Activity_Toko.this.getSharedPreferences(session,MODE_PRIVATE);
        Integer idpelanggan = pref.getInt("idpelanggan",0);
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<mFavorit>> callLike = API.getService().getlike(idpelanggan,getIntent().getIntExtra("id",0));
        callLike.enqueue(new Callback<List<mFavorit>>() {
            @Override
            public void onResponse(Call<List<mFavorit>> call, Response<List<mFavorit>> response) {
                if (response.isSuccessful()) {
                    if (response.body().toString() == "[]") {
                        ada = false;
                        bol = 0;
                    }else {
                        Log.d("oks",callLike.request().url().toString());
                        Log.d("oks",response.body().toString());

                        ada = true;
                        idfavorit = response.body().get(0).getIdfavorit();

                        if (response.body().get(0).getLike() == 1){
                            bind.btnLike.setBackground(getDrawable(R.color.red));
                            bind.btnLike.setTextColor(Color.WHITE);
                            bol =1;
                        }else {
                            bind.btnLike.setBackground(getDrawable(R.drawable.borders));
                            bind.btnLike.setTextColor(Color.BLACK);
                            bol = 0;
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<mFavorit>> call, Throwable t) {
                Toast.makeText(Detail_Activity_Toko.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        Call<List<Komens>> calls = API.getService().getkomens(getIntent().getIntExtra("id",0));
        calls.enqueue(new Callback<List<Komens>>() {
            @Override
            public void onResponse(Call<List<Komens>> call, Response<List<Komens>> response) {
                data = new ArrayList<>();
                data = response.body();
                bind.rcvkomen.setAdapter(new KomenAdapter(data,Detail_Activity_Toko.this));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<Komens>> call, Throwable t) {
                Toast.makeText(Detail_Activity_Toko.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


        Call<List<Hewan>> call = API.getService().getFilterHewan(getIntent().getIntExtra("id",0));
        call.enqueue(new Callback<List<Hewan>>() {
            @Override
            public void onResponse(Call<List<Hewan>> call, Response<List<Hewan>> response) {
                if (response.isSuccessful()){
//                    Log.d("berhasil",response.message());
                    dataHewan = new ArrayList<>();
                    dataHewan = response.body();
                    bind.rcvkewan.setAdapter(new hewanAdapter(Detail_Activity_Toko.this,dataHewan,true));
                    dialog.cancel();
                }else {
                    Log.d("ter","gagal");
                }

            }

            @Override
            public void onFailure(Call<List<Hewan>> call, Throwable t) {
//                Log.d("gagal",t.getMessage());
                Toast.makeText(Detail_Activity_Toko.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
//        dataHewan.add(new Hewan("Sapi Wagyu A5",8000000.0,getIntent().getStringExtra("toko"),getIntent().getStringExtra("alamat"),"","",3.0));
    }

    void setData(List<Komens> data, List<Hewan> dataHewan){
        bind.rcvkomen.setLayoutManager( new LinearLayoutManager(this));
        bind.rcvkomen.setHasFixedSize(true);


        bind.rcvkewan.setLayoutManager( new GridLayoutManager(this,2));
        bind.rcvkewan.setHasFixedSize(true);


    }
}