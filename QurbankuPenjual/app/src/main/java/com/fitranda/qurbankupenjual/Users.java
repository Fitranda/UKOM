package com.fitranda.qurbankupenjual;

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
import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.Daftar;
import com.fitranda.qurbankupenjual.Model.PenjualM;
import com.fitranda.qurbankupenjual.databinding.ActivityUsersBinding;
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

public class Users extends AppCompatActivity {
    ActivityUsersBinding bind;
    private File file = null;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        Integer id = getIntent().getIntExtra("id",0);

        ProgressDialog dialog = new ProgressDialog(Users.this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<PenjualM> call = API.getService().getuser(id);
        Log.d("url",call.request().url().toString());
        call.enqueue(new Callback<PenjualM>() {
            @Override
            public void onResponse(Call<PenjualM> call, Response<PenjualM> response) {
//                Toast.makeText(Users.this, response.body().getPenjual(), Toast.LENGTH_SHORT).show();
                bind.etEmail.setText(response.body().getEmail().toString());
                bind.etAlamat.setText(response.body().getAlamat().toString());
                bind.etNama.setText(response.body().getPenjual().toString());
                bind.etTelp.setText(response.body().getTelp().toString());
                bind.link.setText(response.body().getLink().toString());
                Glide.with(Users.this).load(response.body().getGambar()).into(bind.gambar);
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<PenjualM> call, Throwable t) {
                dialog.cancel();
            }
        });

        bind.btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpans();
            }
        });

        bind.gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Users.this)
                        .crop()
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
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

    void simpans(){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("gambar", file.getName(), requestBody);

        String nama = bind.etNama.getText().toString();
        String telp = bind.etTelp.getText().toString();
        String email = bind.etEmail.getText().toString();
        String alamat = bind.etAlamat.getText().toString();
        String password = bind.etPassword.getText().toString();
        String link = bind.link.getText().toString();
        Integer id = getIntent().getIntExtra("id",0);

        if (nama.isEmpty() || telp.isEmpty() || email.isEmpty() || alamat.isEmpty() || password.isEmpty() || link.isEmpty()){
            Toast.makeText(Users.this, "Harap Isi semua", Toast.LENGTH_SHORT).show();
        }else {
            new EasyWebservice(API.url+"penjual/"+id)
                    .method(Method.POST)
                    .addParam("penjual",nama)
                    .addParam("telp",telp)
                    .addParam("email",email)
                    .addParam("password",password)
                    .addParam("alamat",alamat)
                    .addParam("link",link)
                    .addParam("status",1)
                    .call(new hivatec.ir.easywebservice.Callback.A<String>("pesan") {
                        @Override
                        public void onSuccess(String s) {
                            Toast.makeText(Users.this, s, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(String s) {
                            Toast.makeText(Users.this, s, Toast.LENGTH_SHORT).show();
                        }
                    });
            if (file == null){
                pref = getSharedPreferences(session,MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("email",email);
                editor.putString("telp",telp);
                editor.putString("penjual",nama);
                editor.apply();
                finish();;
            }else{
                String emails = email.replace('.',',');
                Call<Daftar> call = API.getService().pendaftaran(emails,filePart);
                call.enqueue(new retrofit2.Callback<Daftar>() {
                    @Override
                    public void onResponse(Call<Daftar> call, Response<Daftar> response) {
                        Log.d("pesan",response.body().pesan);
                        Toast.makeText(Users.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                        pref = getSharedPreferences(session,MODE_PRIVATE);
                        editor = pref.edit();
                        editor.putString("email",email);
                        editor.putString("telp",telp);
                        editor.putString("penjual",nama);
                        editor.putString("gambar",response.body().gambar);
                        editor.apply();
                    }

                    @Override
                    public void onFailure(Call<Daftar> call, Throwable t) {
                        Log.d("gagal",t.getMessage());
                        Toast.makeText(Users.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        }
    }
}