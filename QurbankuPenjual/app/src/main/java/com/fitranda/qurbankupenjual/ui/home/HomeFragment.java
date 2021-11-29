package com.fitranda.qurbankupenjual.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.fitranda.qurbankupenjual.Adapter.hewanAdapter;
import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Login;
import com.fitranda.qurbankupenjual.TambahHewan;
import com.fitranda.qurbankupenjual.databinding.FragmentHomeBinding;
import com.fitranda.qurbankupenjual.Model.Hewan;
import com.fitranda.qurbankupenjual.databinding.NavHeaderMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String session = "session";


    List<Hewan> data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        Boolean getLogin = pref.getBoolean("logins",false);
        String gambar = pref.getString("gambar","");
        String email = pref.getString("email","");
        String pelanggan = pref.getString("penjual","");

        if (getLogin) {

        }else {
            NavHeaderMainBinding bindings = NavHeaderMainBinding.inflate(LayoutInflater.from(getParentFragment().getContext()));
            bindings.nama.setText(pelanggan);
            bindings.textView.setText(email);
            Glide.with(this).load(gambar).into(bindings.imageView);
            getContext().startActivity(new Intent(getContext(), Login.class));
        }
        initView();
    }

    private void initView() {
        dummydata();
        setData(data);

        binding.btnTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragment().startActivityForResult(new Intent(getContext(), TambahHewan.class),10);
            }
        });
    }

    private void setData(List<Hewan> datas) {
        binding.listHewan.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.listHewan.setHasFixedSize(true);

    }

    private void dummydata() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        pref = getContext().getSharedPreferences(session,MODE_PRIVATE);
        Integer id = pref.getInt("idpenjual",0);

        Call<List<Hewan>> call = API.getService().getKewan(id);
        call.enqueue(new Callback<List<Hewan>>() {
            @Override
            public void onResponse(Call<List<Hewan>> call, Response<List<Hewan>> response) {
                data = new ArrayList<>();
                data = response.body();
                binding.listHewan.setAdapter(new hewanAdapter(data,getContext(),true));
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<Hewan>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

    }
}