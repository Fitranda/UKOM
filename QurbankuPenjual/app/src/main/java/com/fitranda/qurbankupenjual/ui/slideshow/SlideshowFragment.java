package com.fitranda.qurbankupenjual.ui.slideshow;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.fitranda.qurbankupenjual.Bantuan;
import com.fitranda.qurbankupenjual.HistoriPenjualan;
import com.fitranda.qurbankupenjual.Pesanan;
import com.fitranda.qurbankupenjual.R;
import com.fitranda.qurbankupenjual.Users;
import com.fitranda.qurbankupenjual.databinding.FragmentSlideshowBinding;
import com.jakewharton.processphoenix.ProcessPhoenix;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String session = "session";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        Integer id = pref.getInt("idpenjual",0);

        String gambar = pref.getString("gambar","");
        String telp = pref.getString("telp","");
        String email = pref.getString("email","");
        String pelanggan = pref.getString("penjual","");

        binding.tvNamaUser.setText(pelanggan);
        binding.tvEmailUser.setText(email);
        binding.tvHPUser.setText(telp);
        Glide.with(this).load(gambar).into(binding.imageView4);

        binding.btBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), Bantuan.class));
            }
        });

        binding.btHistori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), HistoriPenjualan.class));
            }
        });

        binding.btPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), Pesanan.class));
            }
        });

        binding.users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Users.class);
                i.putExtra("id",id);
                getParentFragment().startActivityForResult(i,10);
            }
        });

        binding.btKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = getContext().getSharedPreferences(session,MODE_PRIVATE);
                editor = pref.edit();
                editor.clear();
                editor.commit();

//                getActivity().finish();
//                System.exit(0);

                ProcessPhoenix.triggerRebirth(getContext());
            }
        });
    }
}