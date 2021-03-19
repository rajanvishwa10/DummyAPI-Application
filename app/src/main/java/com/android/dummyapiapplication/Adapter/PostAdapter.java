package com.android.dummyapiapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dummyapiapplication.Model.PostModel;
import com.android.dummyapiapplication.R;
import com.android.dummyapiapplication.View.CommentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    List<PostModel> postModels;

    public PostAdapter(Context context, List<PostModel> postModels) {
        this.context = context;
        this.postModels = postModels;
    }

    public void setPostList(List<PostModel> postModels) {
        this.postModels = postModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_post, parent, false);
        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        PostModel postModel = postModels.get(position);
        Glide.with(context).load(postModel.getImage()).into(holder.imageView);
        holder.textView.setText(postModel.getText());
        holder.textView2.setText(postModel.getUserModel().getFirstName() + " " + postModel.getUserModel().getLastName());
        Glide.with(context).load(postModel.getUserModel().getPicture()).into(holder.imageView2);

        holder.textView3.setOnClickListener(view -> {
            Intent intent = new Intent(context, CommentActivity.class);
            intent.putExtra("id", postModel.getId());
            context.startActivity(intent);
        });
        // System.out.println(postModel.getUserModelList().size());
    }

    @Override
    public int getItemCount() {
        if (this.postModels != null) {
            return this.postModels.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageView2;
        TextView textView, textView2, textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView2 = itemView.findViewById(R.id.displayImage);
            textView = itemView.findViewById(R.id.text);
            textView2 = itemView.findViewById(R.id.name);
            textView3 = itemView.findViewById(R.id.commentIntent);
        }
    }
}
