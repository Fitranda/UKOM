package com.fitranda.qurbanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.databinding.ActivityForgetPasswordBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hivatec.ir.easywebservice.Callback;
import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.easywebservice.Method;

public class ForgetPassword extends AppCompatActivity {
    ActivityForgetPasswordBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        bind.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pelanggan = bind.etNama.getText().toString();
                String alamat = bind.etAlamat.getText().toString();
                String email = bind.etEmail.getText().toString();
                String password = bind.etPassword.getText().toString();

                if (pelanggan.isEmpty() || alamat.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(ForgetPassword.this, "Harap isi semua", Toast.LENGTH_SHORT).show();
                }else {
                    String regex = "^(.+)@(.+)$";
                    Pattern pattern = Pattern.compile(regex);

                    Matcher matcher = pattern.matcher(email);

                    if (matcher.matches()){
                        new EasyWebservice(API.url+"lupa")
                                .method(Method.POST)
                                .addParam("pelanggan",pelanggan)
                                .addParam("email",email)
                                .addParam("password",password)
                                .addParam("alamat",alamat)
                                .call(new Callback.A<String>("pesan") {
                                    @Override
                                    public void onSuccess(String s) {
                                        Toast.makeText(ForgetPassword.this, s, Toast.LENGTH_SHORT).show();
                                        if (s.equals("Password sudah dirubah !")){
                                            finish();
                                        }

                                    }

                                    @Override
                                    public void onError(String s) {
                                        Toast.makeText(ForgetPassword.this, "Password harus minimal 8 huruf dan kombinasi", Toast.LENGTH_SHORT).show();
                                        Log.d("guagal",s);
                                    }
                                });
                    }else {
                        Toast.makeText(ForgetPassword.this, "Email harus Valid", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

    }
}