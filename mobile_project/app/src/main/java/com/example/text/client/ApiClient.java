package com.example.text.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/";

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                // cho phép nhận các response kiểu plain text
                .addConverterFactory(ScalarsConverterFactory.create())
                // rồi mới đến Gson để parse JSON nếu có
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
