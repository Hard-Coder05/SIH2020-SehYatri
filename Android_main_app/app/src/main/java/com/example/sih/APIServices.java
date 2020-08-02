package com.example.sih;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIServices {
    @POST("register/")
    Call<AuthResponse> postRegisterUser(@Body RegisterDetails registerDetails);

    @POST("login/")
    Call<AuthResponse> postLoginUser(@Body LoginDetails loginDetails);
}
