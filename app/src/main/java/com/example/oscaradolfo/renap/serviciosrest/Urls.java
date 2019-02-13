package com.example.oscaradolfo.renap.serviciosrest;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Urls {
   @POST("/api/renap_movil")
   Call<JsonElement> wsLogin(@Query("email") String email,
                             @Query("password") String password);
   @GET("/api/renap_movil")
   Call<JsonElement> wsInfo(@Query("id") String id);
}
