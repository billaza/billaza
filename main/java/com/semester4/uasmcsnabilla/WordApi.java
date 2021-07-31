package com.semester4.uasmcsnabilla;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public interface WordApi {

    @GET("https://myawesomedictionary.herokuapp.com/words/")
    Call<List<WordResult>> getAllWords(@Query("q") String prefix);


}
