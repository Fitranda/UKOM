package com.fitranda.qurbanku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fitranda.qurbanku.Helper.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.fitranda.qurbanku.databinding.ActivityBottommainBinding;

public class bottommain extends AppCompatActivity {

    private ActivityBottommainBinding binding;
    NavController navController;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        db = new Database(bottommain.this);
        db.buatTabel();

        binding = ActivityBottommainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,  R.id.navigation_notifications,R.id.navigation_dashboard)
                .build();
         navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottommain);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void historipembelian(View view) {
        startActivity(new Intent(this, HistoriPembelian.class));

    }

    public void pesanan(View view) {
        startActivity(new Intent(this, Pesanan.class));
    }

    public void favorit(View view) {
        startActivity(new Intent(this, Favorit.class));
    }

    public void bantuan(View view) {
        startActivity(new Intent(this, Bantuan.class));
    }

    public void users(View view) {
        startActivity(new Intent(this, UserPelanggan.class));
    }

    public void keranjang(View view) {
        startActivity(new Intent(this, Keranjang.class));
    }
    public void Caris(View view) {
        startActivity(new Intent(this, Pencarian.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(this, "Oke", Toast.LENGTH_SHORT).show();
        View view = binding.navView.findViewById(R.id.navigation_home);
        view.performClick();
//        Fragment fr = getSupportFragmentManager().findFragmentByTag("User");
//        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.detach(fr);
//        ft.attach(fr);
//        ft.commit();
//        startActivity(getIntent());
    }
}