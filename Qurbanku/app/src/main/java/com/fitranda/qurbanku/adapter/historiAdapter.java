package com.fitranda.qurbanku.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fitranda.qurbanku.Detail_item_historiActivity;
import com.fitranda.qurbanku.Model.HistoriModel;
import com.fitranda.qurbanku.R;

import java.util.List;

public class historiAdapter extends RecyclerView.Adapter<historiAdapter.ViewHolder> {
    private Context context;
    private List<HistoriModel> historiList;

    public historiAdapter(Context context, List<HistoriModel> historiList) {
        this.context = context;
        this.historiList = historiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_histori,parent,false);
        return new historiAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoriModel notif = historiList.get(position);
        holder.tvJudul.setText(notif.getPenjual());
        holder.tgl.setText( notif.getTanggal());
        holder.tvNo.setText( notif.getNotransaksi());
        Glide.with(context).load(notif.getGambar()).into(holder.gambar);
        holder.clHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Detail_item_historiActivity.class);
                i.putExtra("no",notif.getNotransaksi());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul,tgl,tvNo;
        ImageView gambar;
        ConstraintLayout clHistory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.imageView2);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudulhistori);
            tgl = (TextView) itemView.findViewById(R.id.tglhistori);
            clHistory = itemView.findViewById(R.id.clHistori);
            tvNo = itemView.findViewById(R.id.tvNo);
        }
    }
}
