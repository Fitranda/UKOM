package com.fitranda.qurbanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.DPesanM;
import com.fitranda.qurbanku.Model.KeranjangModel;
import com.fitranda.qurbanku.adapter.DPA;
import com.fitranda.qurbanku.adapter.DPesananAdapter;
import com.fitranda.qurbanku.databinding.ActivityDetailPesananBinding;

import java.util.ArrayList;
import java.util.List;

import hivatec.ir.easywebservice.EasyWebservice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Pesanan extends AppCompatActivity {

    ActivityDetailPesananBinding bind;
    List<DPesanM> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailPesananBinding.inflate(getLayoutInflater()) ;
        setContentView(bind.getRoot());

        bind.list.setHasFixedSize(true);
        bind.list.setLayoutManager(new LinearLayoutManager(Detail_Pesanan.this));



        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<DPesanM>> call = API.getService().getDetail(getIntent().getStringExtra("no"));
        Log.d("url",call.request().url().toString());
        call.enqueue(new Callback<List<DPesanM>>() {
            @Override
            public void onResponse(Call<List<DPesanM>> call, Response<List<DPesanM>> response) {
                data = new ArrayList<DPesanM>();
                data = response.body();
                bind.list.setAdapter(new DPA(Detail_Pesanan.this,data));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<DPesanM>> call, Throwable t) {
                Toast.makeText(Detail_Pesanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


    }

    class getDetail{
        String notransaksi,alamattujuan;
    }
}