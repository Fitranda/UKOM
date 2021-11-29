package com.fitranda.qurbanku.Model;

import android.widget.TextView;

public class HistoriModel {
    private String notransaksi,alamattujuan,total,tanggal,waktu,penjual,pelanggan,gambar;
    private Integer idpenjual,idpelanggan;
    private double bayar,kembali;

    public HistoriModel(String notransaksi, String alamattujuan, String total, String tanggal, String waktu, String penjual, String pelanggan, String gambar, Integer idpenjual, Integer idpelanggan, double bayar, double kembali) {
        this.notransaksi = notransaksi;
        this.alamattujuan = alamattujuan;
        this.total = total;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.penjual = penjual;
        this.pelanggan = pelanggan;
        this.gambar = gambar;
        this.idpenjual = idpenjual;
        this.idpelanggan = idpelanggan;
        this.bayar = bayar;
        this.kembali = kembali;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        this.penjual = penjual;
    }

    public String getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(String pelanggan) {
        this.pelanggan = pelanggan;
    }

    public String getNotransaksi() {
        return notransaksi;
    }

    public void setNotransaksi(String notransaksi) {
        this.notransaksi = notransaksi;
    }

    public String getAlamattujuan() {
        return alamattujuan;
    }

    public void setAlamattujuan(String alamattujuan) {
        this.alamattujuan = alamattujuan;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public Integer getIdpenjual() {
        return idpenjual;
    }

    public void setIdpenjual(Integer idpenjual) {
        this.idpenjual = idpenjual;
    }

    public Integer getIdpelanggan() {
        return idpelanggan;
    }

    public void setIdpelanggan(Integer idpelanggan) {
        this.idpelanggan = idpelanggan;
    }

    public double getBayar() {
        return bayar;
    }

    public void setBayar(double bayar) {
        this.bayar = bayar;
    }

    public double getKembali() {
        return kembali;
    }

    public void setKembali(double kembali) {
        this.kembali = kembali;
    }
}
