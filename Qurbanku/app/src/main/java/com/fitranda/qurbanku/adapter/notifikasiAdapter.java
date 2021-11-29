package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Model.Notifikasi;
import com.fitranda.qurbanku.R;

import java.util.List;

public class notifikasiAdapter extends RecyclerView.Adapter<notifikasiAdapter.ViewHolder> {
    private Context context;
    private List<Notifikasi> notifList;

    public notifikasiAdapter(Context context, List<Notifikasi> notifList) {
        this.context = context;
        this.notifList = notifList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notif,parent,false);
        return new notifikasiAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notifikasi notif = notifList.get(position);
        holder.tvJudul.setText(notif.getNotif());
        holder.tgl.setText(notif.getTanggal()+"  "+notif.getWaktu());
        Glide.with(context).load(notif.getGambar()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return notifList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul,tgl;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView2);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            tgl = (TextView) itemView.findViewById(R.id.tglhistori);
        }
    }
}
