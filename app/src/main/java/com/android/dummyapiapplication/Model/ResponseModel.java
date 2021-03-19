package com.android.dummyapiapplication.Model;

import com.android.dummyapiapplication.Model.PostModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {

    @SerializedName("data")
    @Expose
    private List<PostModel> postModel;

    public List<PostModel> getPostModel() {
        return postModel;
    }

    public void setPostModel(List<PostModel> postModel) {
        this.postModel = postModel;
    }
}
