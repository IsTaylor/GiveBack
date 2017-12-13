package com.example.owner.giveback.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.owner.giveback.R;
import com.example.owner.giveback.data.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 12/12/17.
 */


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    private Context context;
    private List<Post> postList;
    private List<String> postKeys;
    private String uId;
    private Button btnJoin;
    private int lastPosition = -1;
    private DatabaseReference postsRef;

    public PostsAdapter(Context context, String uId, boolean isAdmin){

        this.context = context;
        this.uId = uId;

        postList = new ArrayList<Post>();
        postKeys = new ArrayList<String>();

        postsRef = FirebaseDatabase.getInstance().getReference();

    }

    private void checkAdmin(){
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        postsRef.child("my_app_user").child(userId).child("admin").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean value = (boolean) dataSnapshot.getValue();
                if(!value) {
                    btnJoin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //assign layout xml of one item with the viewHolder
        View row = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_post, parent, false);

        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //set the textViews
        //get data we need to dsplay
        final Post post = postList.get(position);
        holder.tvAuthor.setText(post.getAuthor());
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
        btnJoin = holder.btnJoin;


        if (!uId.equals(post.getUserId())){
            holder.btnDelete.setVisibility(View.INVISIBLE);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removePost(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addPost(Post post, String key){
        postList.add(post);
        postKeys.add(key);
        notifyDataSetChanged();
    }

    public void removePost(int index) {
        postsRef.child("posts").child(postKeys.get(index)).removeValue();

        postList.remove(index);
        postKeys.remove(index);
        notifyItemRemoved(index);
    }

    public void removePostByKey(String key) {
        int index = postKeys.indexOf(key);
        if (index != -1) {
            postList.remove(index);
            postKeys.remove(index);
            notifyItemRemoved(index);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvAuthor;
        public TextView tvTitle;
        public TextView tvBody;
        public Button btnDelete;
        public Button btnJoin;

        public ViewHolder(View itemView) {
            super(itemView);

            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnJoin = itemView.findViewById(R.id.btnJoin);
        }
    }

}
