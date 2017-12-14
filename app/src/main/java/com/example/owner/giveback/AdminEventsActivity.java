package com.example.owner.giveback;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.owner.giveback.adapter.AdminEventsAdapter;
import com.example.owner.giveback.adapter.PostsAdapter;
import com.example.owner.giveback.data.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AdminEventsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private AdminEventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_events);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAdminEvents);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdminEventsAdapter(AdminEventsActivity.this);

        recyclerView.setAdapter(adapter);
        adapter.addUser("Testo", "Testo2");

        initPostsListener();
    }


    private void initPostsListener() {
        DatabaseReference reference =
                FirebaseDatabase.getInstance().getReference();
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        reference.child("posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.addUser("child added", "testo3");
                Post post = dataSnapshot.getValue(Post.class);
                if(post.getUserId() == userId){
                    adapter.addUser("UserID matches", "testo3");
                    for (String userKey: post.getUsersJoined().keySet()){
                        adapter.addUser(post.getUsersJoined().get(userKey), userKey);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);
                if(post.getUserId() == userId){
                    for (String userKey: post.getUsersJoined().keySet()){
                        adapter.removeUser(post.getUsersJoined().get(userKey), userKey);
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
