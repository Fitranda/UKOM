package com.fitranda.qurbanku;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Model.Daftar;
import com.fitranda.qurbanku.databinding.ActivityRegisterBinding;
//import com.github.drjacky.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class Register extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    ActivityRegisterBinding binding;
    private File file = null;
    GoogleApiClient googleApiClient;

    String SiteKey = "6LeMa2AdAAAAAJewSEOEn6SO_WjeScwYgMb7o0Ms";
    String SecretKey = "6LeMa2AdAAAAAPgiz-tgH3dgmQwolLXOY5O81j_F";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Register.this)
                .build();
        googleApiClient.connect();

        binding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkBox.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if (status != null && status.isSuccess()){
                                        Toast.makeText(Register.this, "successs", Toast.LENGTH_SHORT).show();
                                        binding.checkBox.setTextColor(Color.GREEN);
                                    }
                                }
                            });
                }else{
                    binding.checkBox.setTextColor(Color.BLACK);
                }
            }
        });

        binding.etTTL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int tgl = cal.get(Calendar.DAY_OF_MONTH);
                int bln = cal.get(Calendar.MONTH);
                int thn = cal.get(Calendar.YEAR);

                DatePickerDialog dtp = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int thn, int bln, int tgl) {

                        binding.etTTL.setText(thn +"-" +(bln+1)+"-" + tgl);
                    }
                },thn,bln,tgl);

                dtp.show();
            }
        });

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(Register.this, "masuk", Toast.LENGTH_SHORT).show();
                ImagePicker.with(Register.this)
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
            binding.imgLogo.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
                if (result.getResultCode() == RESULT_OK) {
                    Uri uri = result.getData().getData();
                    // Use the uri to load the image
                } else if (result.getResultCode() == ImagePicker.RESULT_ERROR) {
                    // Use ImagePicker.Companion.getError(result.getData()) to show an error
                }
            });


    public void login(View view) {
        finish();
    }

    public void daftar(View view) {

        String nama = binding.etNama.getText().toString();
        String telp = binding.etTelp.getText().toString();
        String email = binding.etEmail.getText().toString();
        String alamat = binding.etAlamat.getText().toString();
        String password = binding.etPassword.getText().toString();
        String ttl = binding.etTTL.getText().toString();

        if (nama.isEmpty() || telp.isEmpty() || email.isEmpty() || alamat.isEmpty() || password.isEmpty() || ttl.isEmpty()){
            Toast.makeText(Register.this, "Harap Isi semua", Toast.LENGTH_SHORT).show();
        }else {

            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(email);

            if (matcher.matches()){

            }

            if (file == null){
                new EasyWebservice(API.url+"pelanggan")
                        .method(Method.POST)
                        .addParam("pelanggan",nama)
                        .addParam("telp",telp)
                        .addParam("email",email)
                        .addParam("password",password)
                        .addParam("alamat",alamat)
                        .addParam("ttl",ttl)
                        .addParam("status",1)
                        .call(new Callback.A<String>("pesan") {
                            @Override
                            public void onSuccess(String s) {
                                Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(String s) {
                                Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
                            }
                        });
            }else {
                MediaType contentType;
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("gambar", file.getName(), requestBody);

                ProgressDialog dialog = new ProgressDialog(Register.this);
                dialog.setMessage("Loading...");
                dialog.setCancelable(false);
                dialog.show();

                new EasyWebservice(API.url+"pelanggan")
                        .method(Method.POST)
                        .addParam("pelanggan",nama)
                        .addParam("telp",telp)
                        .addParam("email",email)
                        .addParam("password",password)
                        .addParam("alamat",alamat)
                        .addParam("ttl",ttl)
                        .addParam("status",1)
                        .call(new Callback.A<String>("pesan") {
                            @Override
                            public void onSuccess(String s) {
                                String emails = email.replace('.',',');

                                Call<Daftar> call = API.getService().pendaftaran(emails,filePart);
                                call.enqueue(new retrofit2.Callback<Daftar>() {
                                    @Override
                                    public void onResponse(Call<Daftar> call, Response<Daftar> response) {
                                        Log.d("pesan",response.body().pesan);
                                        Toast.makeText(Register.this, response.body().pesan, Toast.LENGTH_SHORT).show();
                                        dialog.cancel();
                                    }

                                    @Override
                                    public void onFailure(Call<Daftar> call, Throwable t) {
                                        Log.d("gagal",t.getMessage());
                                        Toast.makeText(Register.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                                        dialog.cancel();
                                    }
                                });
                            }

                            @Override
                            public void onError(String s) {
//                                Toast.makeText(Register.this, s, Toast.LENGTH_SHORT).show();
                                Log.d("gagal",s);
                            }
                        });



            }
            finish();
        }


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}