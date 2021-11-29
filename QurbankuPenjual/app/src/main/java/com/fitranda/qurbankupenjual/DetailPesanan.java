package com.fitranda.qurbankupenjual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fitranda.qurbankupenjual.Adapter.HistoriAdapter;
import com.fitranda.qurbankupenjual.Adapter.hewanAdapter;
import com.fitranda.qurbankupenjual.Model.Hewan;
import com.fitranda.qurbankupenjual.databinding.ActivityDetailPesananBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailPesanan extends AppCompatActivity {
    ActivityDetailPesananBinding bind;
    List<Hewan> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailPesananBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        Boolean ada = getIntent().getBooleanExtra("ada",true);

        if(ada) {
            bind.btnBayar.setVisibility(View.VISIBLE);
        }else {
            bind.btnBayar.setVisibility(View.GONE);
        }

        data = new ArrayList<Hewan>();

        bind.listKeranjang.setLayoutManager(new GridLayoutManager(this,2));
        bind.listKeranjang.setHasFixedSize(true);
        bind.listKeranjang.setAdapter(new hewanAdapter(data,this,false));

        bind.btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(DetailPesanan.this,pembayaran.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailPesanan.this);
                builder.setTitle("PERINGATAN");
                builder.setMessage("Semua Barang Siap Dikirim");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent i = new Intent(DetailPesanan.this,Pesanan.class);
//                                    ProcessPhoenix.triggerRebirth(Keranjang.this,i);s
//                        startActivity(i);

                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
}