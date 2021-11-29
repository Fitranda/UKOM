package com.fitranda.qurbanku.Model;

import com.google.gson.annotations.SerializedName;

public class ModelKewan {
    @SerializedName("idhewan")
    private String idhewan;
    @SerializedName("hewan")
    private String hewan;
    @SerializedName("harga")
    private boolean harga;
    @SerializedName("rincian")
    private String rincian;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("idkategori")
    private Integer idkategori;
    @SerializedName("idpenjual")
    private Integer idpenjual;
    @SerializedName("penjual")
    private String penjual;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("alamat")
    private String alamat;

    public ModelKewan() {}

    public String getIdhewan() {
        return idhewan;
    }

    public void setIdhewan(String idhewan) {
        this.idhewan = idhewan;
    }

    public String getHewan() {
        return hewan;
    }

    public void setHewan(String hewan) {
        this.hewan = hewan;
    }

    public boolean isHarga() {
        return harga;
    }

    public void setHarga(boolean harga) {
        this.harga = harga;
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

    public Integer getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(Integer idkategori) {
        this.idkategori = idkategori;
    }

    public Integer getIdpenjual() {
        return idpenjual;
    }

    public void setIdpenjual(Integer idpenjual) {
        this.idpenjual = idpenjual;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
