package com.semester4.uasmcsnabilla;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class ApiClient {

    private static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myawesomedictionary.herokuapp.com/words/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static WordApi getWordApi() {

        WordApi wordApi = getRetrofit().create(WordApi.class);
        return wordApi;

    }

}