package com.fitranda.qurbanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.Toko;
import com.fitranda.qurbanku.Model.mFavorit;
import com.fitranda.qurbanku.adapter.favoritAdapter;
import com.fitranda.qurbanku.adapter.tokoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favorit extends AppCompatActivity {

    RecyclerView rcvFav;
    favoritAdapter adapter;
    List<mFavorit> listFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        initview();
        isiData();
    }

    private void isiData() {
        ProgressDialog dialog = new ProgressDialog(Favorit.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<mFavorit>> call = API.getService().getFavorit(1);
        call.enqueue(new Callback<List<mFavorit>>() {
            @Override
            public void onResponse(Call<List<mFavorit>> call, Response<List<mFavorit>> response) {
                listFav = new ArrayList<mFavorit>();
                listFav = response.body();
                adapter = new favoritAdapter(Favorit.this,listFav);
                rcvFav.setAdapter(adapter);
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<mFavorit>> call, Throwable t) {
                Toast.makeText(Favorit.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


    }
    private void initview() {
        rcvFav = findViewById(R.id.listfav);
        rcvFav.setLayoutManager( new GridLayoutManager(this,2));
    }
}