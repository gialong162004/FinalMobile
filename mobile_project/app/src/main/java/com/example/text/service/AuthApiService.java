package com.example.text.service;

import com.example.text.dto.LoginRequest;
import com.example.text.dto.User;
import com.example.text.model.ResetPasswordRequest;
import com.example.text.model.SendOtpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApiService {

    @POST("/auth/login")
    Call<User> login(@Body LoginRequest loginRequest);

    @POST("/auth/register")
    Call<User> register(@Body User user);

    /** Gửi OTP bằng JSON {"email": "..."} */
    @POST("auth/send")
    Call<String> sendOtp(@Body SendOtpRequest body);

    /** Reset password bằng JSON */
    @POST("auth/reset-password")
    Call<String> resetPassword(@Body ResetPasswordRequest body);
}
