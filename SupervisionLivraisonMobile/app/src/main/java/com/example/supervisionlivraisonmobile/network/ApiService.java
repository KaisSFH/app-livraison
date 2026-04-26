package com.example.supervisionlivraisonmobile.network;

import com.example.supervisionlivraisonmobile.model.Delivery;
import com.example.supervisionlivraisonmobile.model.LoginRequest;
import com.example.supervisionlivraisonmobile.model.LoginResponse;
import com.example.supervisionlivraisonmobile.model.RegisterRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("api/auth/register")
    Call<Map<String, Object>> register(@Body RegisterRequest request);

    @GET("api/livraisons/livreur/{id}")
    Call<List<Delivery>> getMyDeliveries(@Path("id") int livreurId);

    @PUT("api/livraisons/{nocde}/status")
    Call<Map<String, Object>> updateStatus(@Path("nocde") int nocde, @Body Map<String, String> statusUpdate);
}
