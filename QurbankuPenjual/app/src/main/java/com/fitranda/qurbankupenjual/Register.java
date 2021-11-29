package com.fitranda.qurbankupenjual;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.Daftar;
import com.fitranda.qurbankupenjual.databinding.ActivityRegisterBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Register.this)
                        .crop()
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        binding.btDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Daftar();
            }
        });

        binding.tvkelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
            binding.imgLogo.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    void Daftar(){
//        Toast.makeText(Register.this, "Masuk", Toast.LENGTH_SHORT).show();
        String nama = binding.etNama.getText().toString();
        String telp = binding.etTelp.getText().toString();
        String email = binding.etEmail.getText().toString();
        String alamat = binding.etAlamat.getText().toString();
        String password = binding.etPassword.getText().toString();
        String link = binding.link.getText().toString();
//
        if (nama.isEmpty() || telp.isEmpty() || email.isEmpty() || alamat.isEmpty() || password.isEmpty() || link.isEmpty()){
            Toast.makeText(Register.this, "Harap Isi semua", Toast.LENGTH_SHORT).show();
        }else {

             String regex = "^(.+)@(.+)$";
             Pattern pattern = Pattern.compile(regex);

             Matcher matcher = pattern.matcher(email);

             if (matcher.matches()){

             }


//            ProgressDialog dialog = new ProgressDialog(Register.this);
//            dialog.setMessage("Loading...");
//            dialog.setCancelable(false);
//            dialog.show();

            Log.d("url",API.url+"penjual");

            new EasyWebservice(API.url+"penjual")
                    .method(Method.POST)
                    .addParam("penjual",nama)
                    .addParam("telp",telp)
                    .addParam("email",email)
                    .addParam("password",password)
                    .addParam("alamat",alamat)
                    .addParam("link",link)
                    .addParam("status",1)
                    .addParam("ratings",0)
                    .call(new Callback.A<String>("pesan") {
                        @Override
                        public void onSuccess(String s) {
                            Toast.makeText(Register.this, "berhasil", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
                            Log.d("berhasil",s);
                        }

                        @Override
                        public void onError(String s) {
                            Toast.makeText(Register.this, "gagal", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
                            Log.d("gagal",s);
                        }
                    });
//dialog.cancel();
            if (file == null){
                finish();
            }else{
                String emails = email.replace('.',',');

                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("gambar", file.getName(), requestBody);

                Call<Daftar> call = API.getService().pendaftaran(emails,filePart);
                Log.d("url",call.request().url().toString());
                call.enqueue(new retrofit2.Callback<Daftar>() {
                    @Override
                    public void onResponse(Call<Daftar> call, Response<Daftar> response) {
                        Log.d("pesan",response.body().pesan);
                        Toast.makeText(Register.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Daftar> call, Throwable t) {
                        Log.d("gagal",t.getMessage());
                        Toast.makeText(Register.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        finish();
    }
}