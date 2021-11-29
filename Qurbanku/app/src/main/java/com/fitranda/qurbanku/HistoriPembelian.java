package com.fitranda.qurbanku;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.HistoriModel;
import com.fitranda.qurbanku.adapter.historiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriPembelian extends AppCompatActivity {

    historiAdapter adapter;
    List<HistoriModel> historList;
    private RecyclerView listHistori;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_pembelian);
        initView();
        isiData();
    }

    private void isiData() {
        pref = getSharedPreferences(session,MODE_PRIVATE);
        Integer id = pref.getInt("idpelanggan",0);

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<HistoriModel>> call = API.getService().getorder(id);
        call.enqueue(new Callback<List<HistoriModel>>() {
            @Override
            public void onResponse(Call<List<HistoriModel>> call, Response<List<HistoriModel>> response) {
                historList = new ArrayList<HistoriModel>();
                historList = response.body();
                adapter = new historiAdapter(HistoriPembelian.this,historList);
                listHistori.setAdapter(adapter);
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<HistoriModel>> call, Throwable t) {
                Toast.makeText(HistoriPembelian.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


    }

    private void initView() {
        listHistori = (RecyclerView) findViewById(R.id.listHistori);
        listHistori.setLayoutManager(new LinearLayoutManager(this));
    }
}