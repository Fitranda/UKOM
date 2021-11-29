package com.fitranda.qurbanku.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Detail_Activity_Toko;
import com.fitranda.qurbanku.Model.Toko;
import com.fitranda.qurbanku.R;

import java.util.List;

public class tokoAdapter extends RecyclerView.Adapter<tokoAdapter.ViewHolder> {

    private Context context;
    private List<Toko> tokolist;

    public tokoAdapter(Context context, List<Toko> tokolist) {
        this.context = context;
        this.tokolist = tokolist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_toko,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Toko toko = tokolist.get(position);
        holder.tvJudul.setText(toko.getPenjual());
        holder.tvAlamat.setText(toko.getAlamat());
        holder.ratings.setRating(Float.parseFloat(String.valueOf(toko.getRatings())));
        Glide.with(context).load(toko.getGambar()).into(holder.ivToko);
        holder.tokos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Detail_Activity_Toko.class);
                i.putExtra("toko",toko.getPenjual());
                i.putExtra("gambar",toko.getGambar());
                i.putExtra("alamat",toko.getAlamat());
                i.putExtra("link",toko.getLink());
                i.putExtra("telp",toko.getTelp());
                i.putExtra("ratings",String.valueOf(toko.getRatings()));
                i.putExtra("id",toko.getIdpenjual());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tokolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvJudul,tvAlamat;
        ImageView ivToko;
        CardView tokos;
        RatingBar ratings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivToko = (ImageView) itemView.findViewById(R.id.ivToko);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudulhistori);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            ratings = itemView.findViewById(R.id.ratingBar);
            tokos = itemView.findViewById(R.id.tokos);
        }
    }
}
