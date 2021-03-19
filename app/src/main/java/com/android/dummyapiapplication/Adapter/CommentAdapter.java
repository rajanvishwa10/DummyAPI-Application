package com.android.dummyapiapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dummyapiapplication.Model.CommentModel;
import com.android.dummyapiapplication.Model.PostModel;
import com.android.dummyapiapplication.R;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.http.GET;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context context;
    List<CommentModel> commentModelList;

    public CommentAdapter(Context context, List<CommentModel> commentModelList) {
        this.context = context;
        this.commentModelList = commentModelList;
    }

    public void setComment(List<CommentModel> commentModelList) {
        this.commentModelList = commentModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        CommentModel commentModel = commentModelList.get(position);
        holder.textView.setText(commentModel.getUserModel().getFirstName() + " " + commentModel.getUserModel().getLastName());
        String date = commentModel.getPublishDate();
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        SimpleDateFormat destFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        try {
            Date myDate = myFormat.parse(date);
            String formattedDate = destFormat.format(myDate);
            holder.textView2.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.textView3.setText(commentModel.getMessage());
        Glide.with(context).load(commentModel.getUserModel().getPicture()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (this.commentModelList != null) {
            return this.commentModelList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView, textView2, textView3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.displayImage);
            textView = itemView.findViewById(R.id.name);
            textView2 = itemView.findViewById(R.id.date);
            textView3 = itemView.findViewById(R.id.comment);
        }
    }
}
