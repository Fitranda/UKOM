package com.fitranda.qurbanku.Helper;
import com.fitranda.qurbanku.Model.DPesanM;
import com.fitranda.qurbanku.Model.Daftar;
import com.fitranda.qurbanku.Model.Hewan;
import com.fitranda.qurbanku.Model.HistoriModel;
import com.fitranda.qurbanku.Model.Komens;
import com.fitranda.qurbanku.Model.Notifikasi;
import com.fitranda.qurbanku.Model.Order;
import com.fitranda.qurbanku.Model.PelangganM;
import com.fitranda.qurbanku.Model.PesanM;
import com.fitranda.qurbanku.Model.Toko;
import com.fitranda.qurbanku.Model.mFavorit;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Service {
    @GET("hewan")
    Call<List<Hewan>> getKewan();

    @GET("komen/{id}")
    Call<List<Komens>> getkomens(@Path("id") Integer id);

    @GET("orderh/{id}")
    Call<List<HistoriModel>> getorder(
            @Path("id") Integer id
    );

    @GET("hewan/{id}")
    Call<List<Hewan>> getFilterHewan(
            @Path("id") Integer id
    );

    @GET("hewank/{id}")
    Call<List<Hewan>> getkategoriHewan(
            @Path("id") Integer id
    );

    @GET("orderp/{id}")
    Call<List<PesanM>> getpesanan(
            @Path("id") Integer id
    );

    @GET("hewanc/{id}")
    Call<List<Hewan>> getCariHewan(
            @Path("id") String id
    );

    @GET("favorit/{id}")
    Call<List<mFavorit>> getFavorit(
            @Path("id") Integer id
    );

    @GET("favorit/{id}/{idp}")
    Call<List<mFavorit>> getlike(
            @Path("id") Integer id,
            @Path("idp") Integer idp
    );

    @GET("penjual")
    Call<List<Toko>> getpenjual();

    @GET("login/{id}/{idp}")
    Call<PelangganM> getloginp(
            @Path("id") String id,
            @Path("idp") String idp
    );

    @GET("notifp/{id}")
    Call<List<Notifikasi>> getnotif(
            @Path("id") int id
    );

    @GET("pelanggan/{id}")
    Call<PelangganM> getuser(
            @Path("id") int id
    );

    @POST("order")
    Call<Order> ordering(@Body Order order);

    @POST("pelanggang/{id}")
    @Multipart
    Call<Daftar> pendaftaran(@Path("id") String id, @Part MultipartBody.Part gambar);

    @GET("detail/{id}")
    Call<List<DPesanM>> getDetail(@Path("id") String id);
}
