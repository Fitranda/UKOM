package com.fitranda.qurbanku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.Daftar;
import com.fitranda.qurbanku.Model.PelangganM;
import com.fitranda.qurbanku.databinding.ActivityUserPelangganBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPelanggan extends AppCompatActivity {

    ActivityUserPelangganBinding bind;
    private File file = null;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityUserPelangganBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<PelangganM> call = API.getService().getuser(getIntent().getIntExtra("id",0));
        call.enqueue(new Callback<PelangganM>() {
            @Override
            public void onResponse(Call<PelangganM> call, Response<PelangganM> response) {
                bind.etNama.setText(response.body().getPelanggan());
                bind.etEmail.setText(response.body().getEmail());
                bind.etTelp.setText(response.body().getTelp());
                bind.etAlamat.setText(response.body().getAlamat());
                bind.etTTL.setText(response.body().getTtl());
                Glide.with(UserPelanggan.this).load(response.body().getGambar()).into(bind.gambar);
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<PelangganM> call, Throwable t) {
                Toast.makeText(UserPelanggan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        bind.gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(UserPelanggan.this)
                        .crop()
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        bind.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Simpan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();
//            Toast.makeText(Register.this, uri.toString(), Toast.LENGTH_SHORT).show();
            file = new File(uri.getPath());
            // Use Uri object instead of File to avoid storage permissions
            bind.gambar.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    void Simpan(){

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("gambar", file.getName(), requestBody);

        String nama = bind.etNama.getText().toString();
        String telp = bind.etTelp.getText().toString();
        String email = bind.etEmail.getText().toString();
        String alamat = bind.etAlamat.getText().toString();
        String password = bind.etPassword.getText().toString();
        String ttl = bind.etTTL.getText().toString();
        Integer id = getIntent().getIntExtra("id",0);

        if (nama.isEmpty() || telp.isEmpty() || email.isEmpty() || alamat.isEmpty() || password.isEmpty() || ttl.isEmpty()){
            Toast.makeText(UserPelanggan.this, "Harap Isi semua", Toast.LENGTH_SHORT).show();
        }else {
            new EasyWebservice(API.url+"pelanggan/"+id)
                    .method(Method.POST)
                    .addParam("pelanggan",nama)
                    .addParam("telp",telp)
                    .addParam("email",email)
                    .addParam("password",password)
                    .addParam("alamat",alamat)
                    .addParam("ttl",ttl)
                    .addParam("status",1)
                    .call(new hivatec.ir.easywebservice.Callback.A<String>("pesan") {
                        @Override
                        public void onSuccess(String s) {
                            Toast.makeText(UserPelanggan.this, s, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(String s) {
                            Toast.makeText(UserPelanggan.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
            if (file == null){
                dialog.cancel();
                pref = getSharedPreferences(session,MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("email",email);
                editor.putString("telp",telp);
                editor.putString("pelanggan",nama);
                editor.apply();
                finish();;
            }else{
                String emails = email.replace('.',',');
                Call<Daftar> call = API.getService().pendaftaran(emails,filePart);
                call.enqueue(new retrofit2.Callback<Daftar>() {
                    @Override
                    public void onResponse(Call<Daftar> call, Response<Daftar> response) {
                        Log.d("pesan",response.body().pesan);
                        Toast.makeText(UserPelanggan.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        pref = getSharedPreferences(session,MODE_PRIVATE);
                        editor = pref.edit();
                        editor.putString("email",email);
                        editor.putString("telp",telp);
                        editor.putString("pelanggan",nama);
                        editor.putString("gambar",response.body().gambar);
                        editor.apply();
                    }

                    @Override
                    public void onFailure(Call<Daftar> call, Throwable t) {
                        Log.d("gagal",t.getMessage());
                        Toast.makeText(UserPelanggan.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                finish();
            }
        }
    }
}