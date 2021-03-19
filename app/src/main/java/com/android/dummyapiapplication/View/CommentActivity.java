package com.android.dummyapiapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dummyapiapplication.Adapter.CommentAdapter;
import com.android.dummyapiapplication.Adapter.PostAdapter;
import com.android.dummyapiapplication.Model.CommentModel;
import com.android.dummyapiapplication.Model.PostModel;
import com.android.dummyapiapplication.R;
import com.android.dummyapiapplication.ViewModel.CommentViewModel;
import com.android.dummyapiapplication.ViewModel.PostViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private CommentViewModel commentViewModel;
    private CommentAdapter commentAdapter;
    private RecyclerView recyclerView;
    private List<CommentModel> commentModelList;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Comments");

        textView = findViewById(R.id.textView);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentAdapter = new CommentAdapter(this, commentModelList);
        recyclerView.setAdapter(commentAdapter);

        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        commentViewModel.getCommentListObserver().observe(this, new Observer<List<CommentModel>>() {
            @Override
            public void onChanged(List<CommentModel> commentModel) {
                if(commentModel != null){
                    commentModelList = commentModel;
                    commentAdapter.setComment(commentModelList);
                    if (commentModelList.size() < 1){
                        textView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }
        });
        commentViewModel.makeApiCall(getIntent().getStringExtra("id"));

    }
}