package com.fitranda.qurbankupenjual.Helper;


import com.fitranda.qurbankupenjual.Model.Daftar;
import com.fitranda.qurbankupenjual.Model.Hewan;
import com.fitranda.qurbankupenjual.Model.HistoriModel;
import com.fitranda.qurbankupenjual.Model.Notifikasi;
import com.fitranda.qurbankupenjual.Model.PenjualM;
import com.fitranda.qurbankupenjual.Model.PesanM;
import com.fitranda.qurbankupenjual.Model.PesanOnly;
import com.fitranda.qurbankupenjual.Model.nambahHewan;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Service {
    @GET("hewan/{id}")
    Call<List<Hewan>> getKewan(
            @Path("id") Integer id
    );

    @GET("notift/{id}")
    Call<List<Notifikasi>> getnotif(
            @Path("id") int id
    );

    @GET("orderht/{id}")
    Call<List<HistoriModel>> gethistori(
            @Path("id") int id
    );

    @GET("logint/{id}/{idp}")
    Call<PenjualM> getlogint(
            @Path("id") String id,
            @Path("idp") String idp
    );

    @GET("orderpt/{id}")
    Call<List<PesanM>> getpesan(
            @Path("id") int id
    );

    @GET("penjual/{id}")
    Call<PenjualM> getuser(
            @Path("id") int id
    );

    @POST("hewan")
    Call<nambahHewan> postHewan(@Body nambahHewan hewan);

    @POST("penjualg/{id}")
    @Multipart
    Call<Daftar> pendaftaran(@Path("id") String id, @Part MultipartBody.Part gambar);

    @POST("hewang/{id}")
    @Multipart
    Call<Daftar> gambarhewan(@Path("id") String id, @Part MultipartBody.Part gambar);

    @DELETE("hewan/{id}")
    Call<PesanOnly> hapusHewan(@Path("id") String id);
}
