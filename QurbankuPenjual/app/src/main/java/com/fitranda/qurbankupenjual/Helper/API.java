package com.fitranda.qurbankupenjual.Helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static String url="http://192.168.43.111/apiqurbanku/";
//    public static String url="http://192.168.100.10/apiqurbanku/";
//    private static Retrofit retrofit = null;
//    public static Retrofit getClient() {
//        if ( retrofit == null ){
//            retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
//        }
//        return retrofit;
//    }
    public static Service getService(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        Service service=retrofit.create(Service.class);

        return service;
    }
}
