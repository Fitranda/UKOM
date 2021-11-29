package com.fitranda.qurbanku.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetHewan {
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    List<ModelKewan> listDataHewan;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelKewan> getListDataHewan() {
        return listDataHewan;
    }

    public void setListDataHewan(List<ModelKewan> listDataHewan) {
        this.listDataHewan = listDataHewan;
    }
}
