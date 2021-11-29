package com.fitranda.qurbanku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Helper.Database;
import com.fitranda.qurbanku.Model.KeranjangModel;
import com.fitranda.qurbanku.adapter.keranjangAdapter;
import com.fitranda.qurbanku.databinding.ActivityDetailItemBinding;

import java.util.ArrayList;

public class Detail_Item extends AppCompatActivity {
    public Boolean statuss = true;
    ActivityDetailItemBinding bind;
    Database db;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int AUTH =10;
    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityDetailItemBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ArrayList<String> idhewan = new ArrayList<String>();
        db = new Database(Detail_Item.this);


        Help h = new Help();

        Boolean status = getIntent().getBooleanExtra("BT",true);

        bind.tvNama.setText(getIntent().getStringExtra("hewan"));
        bind.tvHarga.setText(getIntent().getStringExtra("harga"));
        bind.tvAlamat.setText(getIntent().getStringExtra("alamat"));
        bind.tvrincian.setText(getIntent().getStringExtra("rincian"));
        bind.tvToko.setText(getIntent().getStringExtra("toko"));
        Glide.with(this).load(getIntent().getStringExtra("gambar")).into(bind.imageView);
        bind.ratingBar.setRating(Float.parseFloat(getIntent().getStringExtra("ratings")));

        if (status){
            bind.btntbhkr.setVisibility(View.VISIBLE);
        }else{
            bind.btntbhkr.setVisibility(View.GONE);
        }

        bind.btntbhkr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getSharedPreferences(session,MODE_PRIVATE);
                Integer idpelanggan = pref.getInt("idpelanggan",0);

                if (idpelanggan == 0){
                    startActivityForResult(new Intent(Detail_Item.this, Login.class),AUTH);
                }else {
                    Integer jml;
                    String sqls = "SELECT * FROM tblkeranjang order by harga ASC";
                    Cursor cursor = db.select(sqls);
                    if (cursor.getCount() > 0){
                        if (cursor.moveToFirst()){
                            do {
                                idhewan.add(cursor.getString(cursor.getColumnIndex("idhewan")));
                            }while (cursor.moveToNext());
                        }
                    }

                    boolean apa = false;

                    for (String i : idhewan){
                        if (i.equals(getIntent().getStringExtra("id"))){
                            apa = true;
                        }
                    }
//                Toast.makeText(Detail_Item.this, getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();
                    if (apa){
//                    Toast.makeText(Detail_Item.this, "Masuk", Toast.LENGTH_SHORT).show();
                        jml = 1;
                    }else{
                        jml = 0;
                    }

//                jml = 1;

                    if (jml == 1){
                        Toast.makeText(Detail_Item.this, "Hewan sudah dikeranjang", Toast.LENGTH_SHORT).show();
                    }else {
                        String sql = "INSERT INTO tblkeranjang (hewan,harga,toko,alamat,gambar,idhewan,idpenjual) VALUES('"+ bind.tvNama.getText().toString() +"',"+bind.tvHarga.getText().toString()+",'"+ bind.tvToko.getText().toString() +"','"+ bind.tvAlamat.getText().toString() +"','" + getIntent().getStringExtra("gambar") +"','" + getIntent().getStringExtra("id") +"','"+getIntent().getIntExtra("idpenjual",0)+"')";
                        if (db.runSQL(sql)){
                            finish();
                            Intent i = new Intent(Detail_Item.this,Keranjang.class);
                            pref = getSharedPreferences(session,MODE_PRIVATE);
                            editor = pref.edit();
                            editor.putInt("idpenjual",getIntent().getIntExtra("idpenjual",0));
                            editor.apply();
                            startActivity(i);
                        }else {
                            Toast.makeText(Detail_Item.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(new Intent());
    }
}