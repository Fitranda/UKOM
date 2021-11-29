package com.fitranda.qurbanku.ui.dashboard;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Login;
import com.fitranda.qurbanku.R;
import com.fitranda.qurbanku.UserPelanggan;
import com.fitranda.qurbanku.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    int AUTH =10;
    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private boolean getLogin;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pref = getContext().getSharedPreferences(session,MODE_PRIVATE);
        getLogin = pref.getBoolean("logins",false);
        Integer id = pref.getInt("idpelanggan",0);

        String gambar = pref.getString("gambar","");
        String telp = pref.getString("telp","");
        String email = pref.getString("email","");
        String pelanggan = pref.getString("pelanggan","");

        binding.tvNamaUser.setText(pelanggan);
        binding.tvEmailUser.setText(email);
        binding.tvHPUser.setText(telp);
        Glide.with(this).load(gambar).into(binding.imageView4);

        if(getLogin){
        }else{
            getParentFragment().startActivityForResult(new Intent(getContext(), Login.class),AUTH);
        }

        binding.users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), UserPelanggan.class);
                i.putExtra("id",id);
                getParentFragment().startActivityForResult(i,AUTH);
            }
        });


        binding.btKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = pref.edit();
                editor.clear();
                editor.commit();

                getParentFragment().startActivityForResult(new Intent(getContext(), Login.class),AUTH);
            }
        });


        
        
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getContext(), "safa", Toast.LENGTH_SHORT).show();
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
            Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
        }
    }
}