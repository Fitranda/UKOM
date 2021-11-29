package com.fitranda.qurbanku;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.Komens;
import com.fitranda.qurbanku.Model.pilModel;
import com.fitranda.qurbanku.adapter.KomenAdapter;
import com.fitranda.qurbanku.adapter.hewanAdapter;
import com.fitranda.qurbanku.adapter.pilAdapter;
import com.fitranda.qurbanku.databinding.ActivityPencarianBinding;
import com.fitranda.qurbanku.databinding.FilterBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pencarian extends AppCompatActivity {
    ActivityPencarianBinding bind;
    List<Hewan> data;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        bind = ActivityPencarianBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        init();
    }

    void init() {
        dummyData();
        setData();
        bind.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bind.cari.setText("");
                dummyData();
                setData();
            }
        });

        bind.cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Toast.makeText(Pencarian.this, bind.cari.getText().toString(), Toast.LENGTH_SHORT).show();
                if (bind.cari.getText().length() == 0){
                    ProgressDialog dialog = new ProgressDialog(Pencarian.this);
                    dialog.setMessage("Loading...");
                    dialog.setCancelable(false);
                    dialog.show();

                    Call<List<Hewan>> call = API.getService().getKewan();
                    call.enqueue(new Callback<List<Hewan>>() {
                        @Override
                        public void onResponse(Call<List<Hewan>> call, Response<List<Hewan>> response) {
                            data = new ArrayList<>();
                            data = response.body();
                            hewanAdapter adapter = new hewanAdapter(Pencarian.this,data,false);
                            bind.listpil.setAdapter(adapter);
                            dialog.cancel();
                            bind.refresh.setRefreshing(false);
                        }

                        @Override
                        public void onFailure(Call<List<Hewan>> call, Throwable t) {
                            Toast.makeText(Pencarian.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            bind.refresh.setRefreshing(false);
                        }
                    });
                }else{
                    ProgressDialog dialog = new ProgressDialog(Pencarian.this);
                    dialog.setMessage("Loading...");
                    dialog.setCancelable(false);
                    dialog.show();

                    Call<List<Hewan>> call = API.getService().getCariHewan(s.toString());
                    call.enqueue(new Callback<List<Hewan>>() {
                        @Override
                        public void onResponse(Call<List<Hewan>> call, Response<List<Hewan>> response) {
                            data = new ArrayList<>();
                            data = response.body();
                            hewanAdapter adapter = new hewanAdapter(Pencarian.this,data,false);
                            bind.listpil.setAdapter(adapter);
                            dialog.cancel();
                            bind.refresh.setRefreshing(false);
                        }

                        @Override
                        public void onFailure(Call<List<Hewan>> call, Throwable t) {
                            Toast.makeText(Pencarian.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            bind.refresh.setRefreshing(false);
                        }
                    });
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bind.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterBinding fbinding = FilterBinding.inflate(LayoutInflater.from(Pencarian.this));
                dialog = new AlertDialog.Builder(Pencarian.this).create();
                dialog.setView(fbinding.getRoot());
                dialog.setCancelable(true);
                dialog.show();



                fbinding.btnFilter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Pencarian.this, "teds", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    void dummyData(){
        ProgressDialog dialog = new ProgressDialog(Pencarian.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<Hewan>> call = API.getService().getKewan();
        call.enqueue(new Callback<List<Hewan>>() {
            @Override
            public void onResponse(Call<List<Hewan>> call, Response<List<Hewan>> response) {
                data = new ArrayList<>();
                data = response.body();
                hewanAdapter adapter = new hewanAdapter(Pencarian.this,data,false);
                bind.listpil.setAdapter(adapter);
                dialog.cancel();
                bind.refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Hewan>> call, Throwable t) {
                Toast.makeText(Pencarian.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
                bind.refresh.setRefreshing(false);
            }
        });
    }

    void setData(){
        bind.listpil.setLayoutManager( new LinearLayoutManager(this));
        bind.listpil.setHasFixedSize(true);

    }
}