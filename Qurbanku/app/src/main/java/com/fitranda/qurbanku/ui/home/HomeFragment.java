package com.fitranda.qurbanku.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Helper.LoadingDialog;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.Toko;
import com.fitranda.qurbanku.R;
import com.fitranda.qurbanku.adapter.hewanAdapter;
import com.fitranda.qurbanku.adapter.tokoAdapter;
import com.fitranda.qurbanku.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    LoadingDialog loading;

    RecyclerView rcvToko,rcvHewan;
    tokoAdapter adapter;
    hewanAdapter adapterHewan;
    List<Toko> tokoList;
    List<Hewan> hewanlist;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void isiData() {
//        loading.show();
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<Hewan>> call = API.getService().getKewan();
        call.enqueue(new Callback<List<Hewan>>() {
            @Override
            public void onResponse(Call<List<Hewan>> call, Response<List<Hewan>> response) {
                hewanlist = new ArrayList<Hewan>();
                hewanlist = response.body();
                adapterHewan = new hewanAdapter(getContext(),hewanlist,true);
                rcvHewan.setAdapter(adapterHewan);
                dialog.cancel();
//                Log.d("tes",response.body().toString());
//                if (response.isSuccessful()){
//                    hewanlist = response.body();
//                    Log.d("apa",hewanlist.toString());
//                }else{
//                    Log.d("da","gagla");
//                }
            }

            @Override
            public void onFailure(Call<List<Hewan>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        Call<List<Toko>> caller = API.getService().getpenjual();
        Log.d("url penjual",caller.request().url().toString());
        caller.enqueue(new Callback<List<Toko>>() {
            @Override
            public void onResponse(Call<List<Toko>> call, Response<List<Toko>> response) {
                tokoList = new ArrayList<>();
                tokoList = response.body();
                adapter = new tokoAdapter(getContext(),tokoList);
                rcvToko.setAdapter(adapter);
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<Toko>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

//        tokoList= new ArrayList<Toko>();
//        tokoList.add(new Toko("Toko Makmur","Sidoarjo"));
//        tokoList.add(new Toko("Toko Sukses","Surabaya"));
//        tokoList.add(new Toko("Toko Gunung","Gedangan"));
//        tokoList.add(new Toko("Toko Suci","Sidokare"));
//        tokoList.add(new Toko("Toko Oke","Waru"));
//        tokoList.add(new Toko("Toko Apik","Buduran"));
//        tokoList.add(new Toko("Toko Siap","Candi"));
//        adapter = new tokoAdapter(getContext(),tokoList);
//        rcvToko.setAdapter(adapter);


//        hewanlist = new ArrayList<Hewan>();
//        hewanlist.add(new Hewan("Kambing sehat","3000000","Toko Makmur","Sidoarjo"));
//        hewanlist.add(new Hewan("Kambing Putih","2000000","Toko Makmur","Sidoarjo"));
//        hewanlist.add(new Hewan("Kambing Belang","25000000","Toko Makmur","Sidoarjo"));
//        hewanlist.add(new Hewan("Kambing Gunung","35000000","Toko Makmur","Sidoarjo"));
//        hewanlist.add(new Hewan("Kambing Bebas","25000000","Toko Makmur","Sidoarjo"));
//        hewanlist.add(new Hewan("Kambing Besar","4500000","Toko Makmur","Sidoarjo"));
//        hewanlist.add(new Hewan("Kambing Langka","1500000","Toko Makmur","Sidoarjo"));
//        adapterHewan = new hewanAdapter(getContext(),hewanlist,true);
//        rcvHewan.setAdapter(adapterHewan);
//        loading.cancel();

    }

    private void initview() {
//        loading = new LoadingDialog(getContext());
        rcvToko = getActivity().findViewById(R.id.listToko);
        rcvHewan = getActivity().findViewById(R.id.listHewan);
        rcvToko.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcvHewan.setLayoutManager(new GridLayoutManager(getContext(),2));
        rcvHewan.setHasFixedSize(true);
        rcvToko.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        isiData();
        binding.btnSapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hewanK(1);
            }
        });

        binding.btnKambing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hewanK(2);
            }
        });

        binding.btnUnta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hewanK(3);
            }
        });
    }

    private void hewanK(Integer id){
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<Hewan>> call = API.getService().getkategoriHewan(id);
        call.enqueue(new Callback<List<Hewan>>() {
            @Override
            public void onResponse(Call<List<Hewan>> call, Response<List<Hewan>> response) {
                hewanlist = new ArrayList<Hewan>();
                hewanlist = response.body();
                adapterHewan = new hewanAdapter(getContext(),hewanlist,true);
                rcvHewan.setAdapter(adapterHewan);
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