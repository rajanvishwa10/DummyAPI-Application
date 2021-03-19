package com.android.dummyapiapplication.Network;

import com.android.dummyapiapplication.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIService {
    @Headers({
            "app-id: 6053ad1aefb02db6b6014d4c"
    })
    @GET("data/api/post")
    Call<ResponseModel> getPostList();

}
