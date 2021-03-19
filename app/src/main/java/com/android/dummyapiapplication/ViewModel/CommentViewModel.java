package com.android.dummyapiapplication.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.dummyapiapplication.Model.CommentModel;
import com.android.dummyapiapplication.Model.CommentResponse;
import com.android.dummyapiapplication.Model.PostModel;
import com.android.dummyapiapplication.Model.ResponseModel;
import com.android.dummyapiapplication.Network.APIService;
import com.android.dummyapiapplication.Network.APIServiceComment;
import com.android.dummyapiapplication.Network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends ViewModel {
    private final MutableLiveData<List<CommentModel>> commentList;

    public CommentViewModel() {
        commentList = new MutableLiveData<>();
    }
    public MutableLiveData<List<CommentModel>> getCommentListObserver(){
        return commentList;
    }

    public void makeApiCall(String id){
        APIServiceComment apiService = RetroInstance.getRetrofitClient().create(APIServiceComment.class);
        Call<CommentResponse> call = apiService.getCommentList(id);
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                commentList.postValue(response.body().getCommentModelList());
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {

            }
        });

    }
}
