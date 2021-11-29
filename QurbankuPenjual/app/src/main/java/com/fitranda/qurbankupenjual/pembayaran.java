package com.fitranda.qurbankupenjual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.fitranda.qurbankupenjual.databinding.ActivityPembayaranBinding;

public class pembayaran extends AppCompatActivity {
    ActivityPembayaranBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPembayaranBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    binding.akun.setVisibility(view.GONE);
                } else if (position == 1) {
                    binding.akun.setVisibility(view.VISIBLE);
                    binding.EAkun.setVisibility(view.GONE);
                    binding.akunBank.setVisibility(view.VISIBLE);
                } else if (position == 2) {
                    binding.akun.setVisibility(view.VISIBLE);
                    binding.EAkun.setVisibility(view.VISIBLE);
                    binding.akunBank.setVisibility(view.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(pembayaran.this);
                builder.setTitle("PERINGATAN");
                builder.setMessage("Apakah yakin melakukan transaksi?");
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(pembayaran.this, "Bayar Berhasil", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });
    }
}