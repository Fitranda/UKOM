package com.fitranda.qurbankupenjual;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.Daftar;
import com.fitranda.qurbankupenjual.Model.nambahHewan;
import com.fitranda.qurbankupenjual.databinding.ActivityTambahHewanBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class TambahHewan extends AppCompatActivity {

    ActivityTambahHewanBinding bind;
    Integer idkategori = 1;

    private File file = null;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityTambahHewanBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.spHewan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    idkategori = 1;
                }else if (position == 1) {
                    idkategori = 2;
                }else if (position == 2) {
                    idkategori = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bind.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(TambahHewan.this)
                        .crop()
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        bind.btnSimpans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pref = getSharedPreferences(session,MODE_PRIVATE);
                Integer id = pref.getInt("idpenjual",0);

                String idhewan = bind.etKodeHewan.getText().toString();

                String hewan = bind.etNama.getText().toString();

                String rincian = bind.etRincian.getText().toString();
                String harga = bind.etHarga.getText().toString();
//                Toast.makeText(TambahHewan.this, String.valueOf(id), Toast.LENGTH_SHORT).show();

                if (idhewan.isEmpty() || hewan.isEmpty() || rincian.isEmpty() || harga.isEmpty() || file == null){
                    Toast.makeText(TambahHewan.this, "Harap Isi semua", Toast.LENGTH_SHORT).show();
                }else {
                    new EasyWebservice(API.url+"hewan")
                            .method(Method.POST)
                            .addParam("idkategori",idkategori)
                            .addParam("idhewan",idhewan)
                            .addParam("hewan",hewan)
                            .addParam("rincian",rincian)
                            .addParam("idpenjual",id)
                            .addParam("flagjual",0)
                            .addParam("harga",harga)
                            .call(new Callback.A<String>("pesan") {
                                @Override
                                public void onSuccess(String s) {
//                                    Toast.makeText(TambahHewan.this, s, Toast.LENGTH_SHORT).show();
                                    Log.d("ntah",s);
                                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("gambar", file.getName(), requestBody);

                                    Call<Daftar> call = API.getService().gambarhewan(idhewan,filePart);
                                    Log.d("URL GH", call.request().url().toString());
                                    call.enqueue(new retrofit2.Callback<Daftar>() {
                                        @Override
                                        public void onResponse(Call<Daftar> call, Response<Daftar> response) {
                                            Log.d("pesan",response.body().pesan);
                                            Toast.makeText(TambahHewan.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                                            finish();
                                        }

                                        @Override
                                        public void onFailure(Call<Daftar> call, Throwable t) {
                                            Log.d("gagal",t.getMessage());
                                            Toast.makeText(TambahHewan.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onError(String s) {
                                    Log.d("gasxdf",s);
//                                    Toast.makeText(TambahHewan.this, s, Toast.LENGTH_SHORT).show();
                                }
                            });


                }

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
            bind.imageView2.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    void Simpan(){

        

//        if (idhewan.isEmpty() || hewan.isEmpty() || rincian.isEmpty() || harga == 0 || file == null){
//            Toast.makeText(TambahHewan.this, "Harap Isi semua", Toast.LENGTH_SHORT).show();
//        }

    }
}