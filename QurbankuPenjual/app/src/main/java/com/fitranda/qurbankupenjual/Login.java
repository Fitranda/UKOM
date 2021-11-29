package com.fitranda.qurbankupenjual;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.PelM;
import com.fitranda.qurbankupenjual.Model.PenjualM;
import com.fitranda.qurbankupenjual.databinding.ActivityLoginBinding;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;
import retrofit2.Call;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    ActivityLoginBinding bind;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.cirloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emails = bind.txtEmail.getText().toString();
                String password = bind.txtPassword.getText().toString();
                String email = emails.replace('.',',');

                ProgressDialog dialog = new ProgressDialog(Login.this);
                dialog.setMessage("Loading...");
                dialog.setCancelable(false);
                dialog.show();

//                Call<PenjualM> call = API.getService().getlogint(email,password);
//                call.enqueue(new Callback<PenjualM>() {
//                    @Override
//                    public void onResponse(Call<PenjualM> call, Response<PenjualM> response) {
//                        pref = getSharedPreferences(session,MODE_PRIVATE);
//                        editor = pref.edit();
//                        editor.putBoolean("logins",true);
//                        editor.putInt("idpenjual",response.body().getIdpenjual().intValue());
//                        editor.putString("email",response.body().getEmail().toString());
//                        editor.putString("telp",response.body().getTelp().toString());
//                        editor.putString("gambar",response.body().getGambar().toString());
//                        editor.putString("penjual",response.body().getPenjual().toString());
//                        editor.apply();
//                        dialog.cancel();
//                        finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<PenjualM> call, Throwable t) {
//                        Log.d("error",t.getMessage());
//                        Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                        dialog.cancel();
//                    }
//                });

                new EasyWebservice(API.url + "loginpenjual")
                        .method(Method.POST)
                        .addParam("email",emails)
                        .addParam("password",password)
                        .call(new Callback.AB<String, PelM>("pesan","data") {
                            @Override
                            public void onSuccess(String s, PelM pelM) {
                                if (pelM == null){
                                    Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }else {
                                    dialog.cancel();
                                    pref = getSharedPreferences(session,MODE_PRIVATE);
                                    editor = pref.edit();
                                    editor.putBoolean("logins",true);
                                    Log.d("tes",pelM.getIdpenjual().toString());
                                    editor.putInt("idpenjual",pelM.getIdpenjual().intValue());
                                    editor.putString("email",pelM.getEmail().toString());
                                    editor.putString("telp",pelM.getTelp().toString());
                                    editor.putString("gambar",pelM.getGambar().toString());
                                    editor.putString("penjual",pelM.getPenjual().toString());
                                    editor.apply();
                                    finish();
                                }
                            }

                            @Override
                            public void onError(String s) {
                                dialog.cancel();
                                Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        bind.tvkedaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Login.this.finishAffinity();
    }
}