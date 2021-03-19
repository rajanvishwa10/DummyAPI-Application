package com.android.dummyapiapplication.Network;

import com.android.dummyapiapplication.Model.CommentResponse;
import com.android.dummyapiapplication.Model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface APIServiceComment {
    @Headers({
            "app-id: 6053ad1aefb02db6b6014d4c"
    })
    @GET("data/api/post/{id}/comment")
    Call<CommentResponse> getCommentList(
            @Path("id") String id
    );
}
