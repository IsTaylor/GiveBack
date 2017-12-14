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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 12/13/17.
 */

public class AdminEventsAdapter extends RecyclerView.Adapter<AdminEventsAdapter.ViewHolder> {

    private List<String> userList; //usernames
    private List<String> userKeys; //???
    private DatabaseReference postsRef;

    public AdminEventsAdapter(Context context){

        postsRef = FirebaseDatabase.getInstance().getReference();

        userList = new ArrayList<>();
        userKeys = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //assign layout xml of one item with the viewHolder
        View row = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_admin_event, parent, false);

        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(AdminEventsAdapter.ViewHolder holder, int position) {
        String userName = userList.get(position);
        holder.tvUsername.setText(userName);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addUser(String name, String key){
        userList.add(name);
        userKeys.add(key);
        notifyDataSetChanged();
    }

    public void removeUser(String name, String key){
        userList.remove(name);
        userKeys.remove(key);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvUsername;


        public ViewHolder(View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);

        }
    }
}
