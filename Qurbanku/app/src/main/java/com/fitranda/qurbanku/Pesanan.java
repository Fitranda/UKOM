package com.fitranda.qurbanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.HistoriModel;
import com.fitranda.qurbanku.Model.PesanM;
import com.fitranda.qurbanku.adapter.PesanAdapter;
import com.fitranda.qurbanku.adapter.historiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pesanan extends AppCompatActivity {

    PesanAdapter adapter;
    List<PesanM> historList;
    private RecyclerView listPesanan;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);
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

        Call<List<PesanM>> call = API.getService().getpesanan(id);
        call.enqueue(new Callback<List<PesanM>>() {
            @Override
            public void onResponse(Call<List<PesanM>> call, Response<List<PesanM>> response) {
                historList = new ArrayList<PesanM>();
                historList = response.body();
                adapter = new PesanAdapter(Pesanan.this,historList);
                listPesanan.setAdapter(adapter);
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<PesanM>> call, Throwable t) {
                Toast.makeText(Pesanan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


    }

    private void initView() {
        listPesanan = (RecyclerView) findViewById(R.id.listPesan);
        listPesanan.setHasFixedSize(true);
        listPesanan.setLayoutManager(new LinearLayoutManager(this));
    }
}