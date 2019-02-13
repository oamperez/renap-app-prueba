package com.example.oscaradolfo.renap.serviciosrest;

import com.example.oscaradolfo.renap.serviciosrest.Urls;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by OscarAdolfo on 20/09/2017.
 */

public class Retrofit {
    public static String ip = "http://192.168.1.74:8000";
    public static Urls urls;
    public static Urls getUrls(){
        if(urls==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);
            builder.connectTimeout(180, TimeUnit.SECONDS);
            builder.readTimeout(180, TimeUnit.SECONDS);
            builder.writeTimeout(180, TimeUnit.SECONDS);
            retrofit2.Retrofit restAdapter = new retrofit2.Retrofit.Builder()
                    .baseUrl(ip)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            urls = restAdapter.create(Urls.class);
        }
        return urls;
    }
}
