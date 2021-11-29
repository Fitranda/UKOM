package com.fitranda.qurbanku.Model;

public class Order {
    private String notransaksi,alamattujuan,tanggal,waktu;
    private Integer idpenjual,idpelanggan,total,flagkirim;

    public Order(String notransaksi, String alamattujuan, String tanggal, String waktu, Integer idpenjual, Integer idpelanggan, Integer total, Integer flagkirim) {
        this.notransaksi = notransaksi;
        this.alamattujuan = alamattujuan;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.idpenjual = idpenjual;
        this.idpelanggan = idpelanggan;
        this.total = total;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getFlagkirim() {
        return flagkirim;
    }

    public void setFlagkirim(Integer flagkirim) {
        this.flagkirim = flagkirim;
    }
}
