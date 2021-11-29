package com.fitranda.qurbankupenjual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fitranda.qurbankupenjual.databinding.ActivityBantuanBinding;

public class Bantuan extends AppCompatActivity {

    ActivityBantuanBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityBantuanBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }
}