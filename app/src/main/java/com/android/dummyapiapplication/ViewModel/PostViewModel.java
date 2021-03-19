package com.android.dummyapiapplication.ViewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.dummyapiapplication.Model.PostModel;
import com.android.dummyapiapplication.Model.UserModel;
import com.android.dummyapiapplication.Network.APIService;
import com.android.dummyapiapplication.Model.ResponseModel;
import com.android.dummyapiapplication.Network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private final MutableLiveData<List<PostModel>> postModelList;

    public PostViewModel(){
        postModelList = new MutableLiveData<>();
    }
    public MutableLiveData<List<PostModel>> getPostListObserver(){
        return postModelList;
    }

    public void makeApiCall(){
        APIService apiService = RetroInstance.getRetrofitClient().create(APIService.class);
        Call<ResponseModel> call = apiService.getPostList();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                postModelList.postValue(response.body().getPostModel());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                postModelList.postValue(null);
            }
        });
    }
}
