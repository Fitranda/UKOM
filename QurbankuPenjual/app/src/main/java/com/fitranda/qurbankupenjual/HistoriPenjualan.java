package com.fitranda.qurbankupenjual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.fitranda.qurbankupenjual.Adapter.HistoriAdapter;
import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.HistoriModel;
import com.fitranda.qurbankupenjual.databinding.ActivityHistoriPenjualanBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriPenjualan extends AppCompatActivity {
    ActivityHistoriPenjualanBinding bind;
    List<HistoriModel> data;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityHistoriPenjualanBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        init();
    }

    public void init(){
        dummy();
        setdata(data);
    }

    private void setdata(List<HistoriModel> data) {
        bind.listhistori.setLayoutManager(new LinearLayoutManager(this));
        bind.listhistori.setHasFixedSize(true);

    }

    public void dummy(){

        ProgressDialog dialog = new ProgressDialog(HistoriPenjualan.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        pref = getSharedPreferences(session,MODE_PRIVATE);
        Integer id = pref.getInt("idpenjual",0);

        Call<List<HistoriModel>> call = API.getService().gethistori(id);
        call.enqueue(new Callback<List<HistoriModel>>() {
            @Override
            public void onResponse(Call<List<HistoriModel>> call, Response<List<HistoriModel>> response) {
                data = new ArrayList<HistoriModel>();
                data = response.body();
                bind.listhistori.setAdapter(new HistoriAdapter(data,HistoriPenjualan.this,false));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<HistoriModel>> call, Throwable t) {
                dialog.cancel();
            }
        });
    }
}