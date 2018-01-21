package com.example.team.hackathon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class posted_activity extends AppCompatActivity {

    private RecyclerView mBloglist;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_activity);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mBloglist = (RecyclerView) findViewById(R.id.blog_list);
        mBloglist.setHasFixedSize(true);
        mBloglist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firbaseAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class, R.layout.blog_row, BlogViewHolder.class, mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(), model.getImage());
            }
        };
        mBloglist.setAdapter(firbaseAdapter);
    }
    public static  class BlogViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }
        public void setDesc(String desc){
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }
    }
}
