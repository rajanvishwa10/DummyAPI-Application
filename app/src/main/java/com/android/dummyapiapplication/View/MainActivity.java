package com.android.dummyapiapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.dummyapiapplication.Adapter.PostAdapter;
import com.android.dummyapiapplication.Model.PostModel;
import com.android.dummyapiapplication.R;
import com.android.dummyapiapplication.ViewModel.PostViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PostViewModel postViewModel;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private List<PostModel> postModelList;

    //6053ad1aefb02db6b6014d4c
    //String url = "https://dummyapi.io/data/api/post";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Posts");

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter = new PostAdapter(this, postModelList);
        recyclerView.setAdapter(postAdapter);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPostListObserver().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                if(postModels != null){
                    postModelList = postModels;
                    postAdapter.setPostList(postModels);
                }
            }
        });
        postViewModel.makeApiCall();
//

    }


}