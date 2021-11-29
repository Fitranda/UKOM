package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Detail_Item;
import com.fitranda.qurbanku.Help;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.R;

import java.util.List;

public class hewanAdapter extends RecyclerView.Adapter<hewanAdapter.ViewHolder>{
    private Context context;
    private List<Hewan> hewanlist;
    private Boolean status;

    public hewanAdapter(Context context, List<Hewan> hewanlist, Boolean status) {
        this.context = context;
        this.hewanlist = hewanlist;
        this.status = status;
    }

    @NonNull
    @Override
    public hewanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_hewan,parent,false);
        return new hewanAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull hewanAdapter.ViewHolder holder, int position) {
        Hewan hewan = hewanlist.get(position);
        holder.tvJudul.setText(hewan.getHewan());
        holder.tvAlamat.setText(hewan.getAlamat());
        holder.tvHarga.setText(String.valueOf(Math.round(hewan.getHarga())));
        holder.tvToko.setText(hewan.getPenjual());
        Glide.with(context).load(hewan.getGambar()).into(holder.ivToko);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Detail_Item.class);
                i.putExtra("BT",status);
                i.putExtra("hewan",hewan.getHewan());
                i.putExtra("harga",String.valueOf(Math.round(hewan.getHarga())));
                i.putExtra("toko",hewan.getPenjual());
                i.putExtra("alamat",hewan.getAlamat());
                i.putExtra("gambar",hewan.getGambar());
                i.putExtra("rincian",hewan.getRincian());
                i.putExtra("ratings",String.valueOf(hewan.getRatings()));
                i.putExtra("id",hewan.getIdhewan());
                i.putExtra("idpenjual",hewan.getIdpenjual());
                context.startActivity(i);
                Help dt = new Help();
                dt.s = status;
            }
        });
    }

    @Override
    public int getItemCount() {
        return hewanlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul,tvAlamat,tvToko,tvHarga;
        CardView item;
        ImageView ivToko;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            ivToko = (ImageView) itemView.findViewById(R.id.ivToko);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudulhistori);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            tvToko = (TextView) itemView.findViewById(R.id.tvToko);
            tvHarga = (TextView) itemView.findViewById(R.id.tvHarga);
        }
    }
}
