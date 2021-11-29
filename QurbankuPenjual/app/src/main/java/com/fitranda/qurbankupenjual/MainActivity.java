package com.fitranda.qurbankupenjual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fitranda.qurbankupenjual.databinding.NavHeaderMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.fitranda.qurbankupenjual.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        NavHeaderMainBinding bindings = NavHeaderMainBinding.inflate(LayoutInflater.from(MainActivity.this));
//        Toast.makeText(this, bindings.textView.getText().toString(), Toast.LENGTH_SHORT).show();
//        pref = getSharedPreferences(session,MODE_PRIVATE);
//        Boolean getLogin = pref.getBoolean("logins",false);
//        String gambar = pref.getString("gambar","");
//        String email = pref.getString("email","");
//        String pelanggan = pref.getString("penjual","");
//
//        Log.d("pel",pelanggan);
//        Log.d("pelanggan",bindings.textView.getText().toString());
//
//        bindings.nama.setText(pelanggan);
//        bindings.textView.setText(email);
//        Glide.with(this).load(gambar).into(bindings.imageView);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        View view = binding.navView.findViewById(R.id.nav_home);
        view.performClick();
    }

    public void refresfgj(){
        View view = binding.navView.findViewById(R.id.nav_home);
        view.performClick();
    }
}