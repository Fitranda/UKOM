package com.fitranda.qurbanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.DPesanM;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.HistoriModel;
import com.fitranda.qurbanku.adapter.DPA;
import com.fitranda.qurbanku.adapter.hewanAdapter;
import com.fitranda.qurbanku.adapter.historiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_item_historiActivity extends AppCompatActivity {
    DPA adapter;
    List<DPesanM> data;
    private RecyclerView rcvDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item_histori);
        initView();
        isiData();
    }

    private void isiData() {
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
                rcvDetail.setAdapter(new DPA(Detail_item_historiActivity.this,data));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<DPesanM>> call, Throwable t) {
                Toast.makeText(Detail_item_historiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });;

    }

    private void initView() {
        rcvDetail = (RecyclerView) findViewById(R.id.listdetail);
        rcvDetail.setLayoutManager(new LinearLayoutManager(this));

    }
}