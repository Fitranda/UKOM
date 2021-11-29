package com.fitranda.qurbankupenjual.Model;

public class PesanM {
    private String notransaksi,alamattujuan,total,tanggal,waktu,penjual,pelanggan,gambar;
    private Integer idpenjual,idpelanggan,flagkirim;
    private double bayar,kembali;

    public PesanM(String notransaksi, String alamattujuan, String total, String tanggal, String waktu, String penjual, String pelanggan, String gambar, Integer idpenjual, Integer idpelanggan, Integer flagkirim, double bayar, double kembali) {
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
        this.flagkirim = flagkirim;
        this.bayar = bayar;
        this.kembali = kembali;
    }

    public Integer getFlagkirim() {
        return flagkirim;
    }

    public void setFlagkirim(Integer flagkirim) {
        this.flagkirim = flagkirim;
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

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
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
