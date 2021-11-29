package com.fitranda.qurbankupenjual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.fitranda.qurbankupenjual.Adapter.HistoriAdapter;
import com.fitranda.qurbankupenjual.Adapter.PesanA;
import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.HistoriModel;
import com.fitranda.qurbankupenjual.Model.PesanM;
import com.fitranda.qurbankupenjual.databinding.ActivityPesananBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pesanan extends AppCompatActivity {
    ActivityPesananBinding bind;
    List<PesanM> data;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityPesananBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());



        ProgressDialog dialog = new ProgressDialog(Pesanan.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        pref = getSharedPreferences(session,MODE_PRIVATE);
        Integer id = pref.getInt("idpenjual",0);

        Call<List<PesanM>> call = API.getService().getpesan(id);
        call.enqueue(new Callback<List<PesanM>>() {
            @Override
            public void onResponse(Call<List<PesanM>> call, Response<List<PesanM>> response) {
                data = new ArrayList<PesanM>();
                data = response.body();
                bind.listpesanan.setAdapter(new PesanA(data,Pesanan.this));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<PesanM>> call, Throwable t) {
            dialog.cancel();
            }
        });

        bind.listpesanan.setLayoutManager(new LinearLayoutManager(this));
        bind.listpesanan.setHasFixedSize(true);

    }
}