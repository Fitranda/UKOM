package com.fitranda.qurbankupenjual.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fitranda.qurbankupenjual.databinding.ItemCardHewanBinding;

import java.util.Iterator;
import java.util.List;

public class Hewan {
    private String hewan,idhewan,rincian,gambar,kategori;
    private Integer harga,flagjual;

    public Hewan(String hewan, String idhewan, String rincian, String gambar, String kategori, Integer harga, Integer flagjual) {
        this.hewan = hewan;
        this.idhewan = idhewan;
        this.rincian = rincian;
        this.gambar = gambar;
        this.kategori = kategori;
        this.harga = harga;
        this.flagjual = flagjual;
    }

    public String getHewan() {
        return hewan;
    }

    public void setHewan(String hewan) {
        this.hewan = hewan;
    }

    public String getIdhewan() {
        return idhewan;
    }

    public void setIdhewan(String idhewan) {
        this.idhewan = idhewan;
    }

    public String getRincian() {
        return rincian;
    }

    public void setRincian(String rincian) {
        this.rincian = rincian;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getFlagjual() {
        return flagjual;
    }

    public void setFlagjual(Integer flagjual) {
        this.flagjual = flagjual;
    }
}


