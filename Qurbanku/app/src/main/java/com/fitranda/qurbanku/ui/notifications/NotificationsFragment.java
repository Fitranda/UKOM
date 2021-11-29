package com.fitranda.qurbanku.ui.notifications;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fitranda.qurbanku.Helper.API;
import com.fitranda.qurbanku.Login;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.Notifikasi;
import com.fitranda.qurbanku.Pencarian;
import com.fitranda.qurbanku.R;
import com.fitranda.qurbanku.adapter.hewanAdapter;
import com.fitranda.qurbanku.adapter.notifikasiAdapter;
import com.fitranda.qurbanku.adapter.tokoAdapter;
import com.fitranda.qurbanku.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    RecyclerView rcvNotif;
    notifikasiAdapter adapter;
    List<Notifikasi> notifList;
    int AUTH =10;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public static final String session = "session";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        initview();
        isiData();
        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initview();
                isiData();
            }
        });
    }

    private void initview() {
        rcvNotif = getActivity().findViewById(R.id.listNotif);
        rcvNotif.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void isiData() {
        pref = getContext().getSharedPreferences(session,MODE_PRIVATE);
        Integer idpelanggan = pref.getInt("idpelanggan",0);

        if (idpelanggan == 0){
            getParentFragment().startActivityForResult(new Intent(getContext(), Login.class),AUTH);
        }


        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Call<List<Notifikasi>> call = API.getService().getnotif(idpelanggan);
        call.enqueue(new Callback<List<Notifikasi>>() {
            @Override
            public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                notifList = new ArrayList<Notifikasi>();
                notifList = response.body();
                notifikasiAdapter adapter = new notifikasiAdapter(getContext(),notifList);
                binding.listNotif.setAdapter(adapter);
                dialog.cancel();
                binding.refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
                binding.refresh.setRefreshing(false);
            }
        });
    }
}