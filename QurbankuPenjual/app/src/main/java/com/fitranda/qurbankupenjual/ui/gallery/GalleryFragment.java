package com.fitranda.qurbankupenjual.ui.gallery;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
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

import com.fitranda.qurbankupenjual.Adapter.notifikasiAdapter;
import com.fitranda.qurbankupenjual.Helper.API;
import com.fitranda.qurbankupenjual.Model.Notifikasi;
import com.fitranda.qurbankupenjual.R;
import com.fitranda.qurbankupenjual.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    RecyclerView rcvNotif;
    notifikasiAdapter adapter;
    List<Notifikasi> notifList;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String session = "session";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
    }

    private void initview() {
        rcvNotif = getActivity().findViewById(R.id.listNotif);
        rcvNotif.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void isiData() {


        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        pref = getContext().getSharedPreferences(session,MODE_PRIVATE);
        Integer id = pref.getInt("idpenjual",0);

        Call<List<Notifikasi>> call = API.getService().getnotif(id);
        call.enqueue(new Callback<List<Notifikasi>>() {
            @Override
            public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                notifList = new ArrayList<Notifikasi>();
                notifList = response.body();
                adapter = new notifikasiAdapter(getContext(),notifList);
                rcvNotif.setAdapter(adapter);
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });



    }
}