package com.example.owner.giveback;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.owner.giveback.adapter.ViewPagerAdapter;
import com.example.owner.giveback.data.userData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NewUserActivity extends AppCompatActivity {

    ViewPager mPager;
    ProgressBar progress;
    int position;

    private String name;
    private boolean admin;

    boolean userAdmin;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        progress = (ProgressBar) findViewById(R.id.progressBar);
        position = 33;

        mPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int pos) {
                //This is because progress is 0 at the start of the program


                position = position + 33;
                progress.setProgress(position);
            }

        });
    }

    private void writeNewUser() {
        userData myUser = new userData(name, admin);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.child("my_app_user").child(userId).setValue(myUser).addOnCompleteListener(NewUserActivity.this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(NewUserActivity.this, "Success",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public void cbOnChanged(boolean b) {
        this.admin = b;
    }

    public void fragOneChanged(String text) {
        this.name = text;
    }


    //NAVIGATION
    public void jumpToPage(View view) {
        mPager.setCurrentItem(1);
    }

    public void jumpToPage2(View view) {
        mPager.setCurrentItem(2);
    }

    public void jumpToPageDone(View view) {
        //SET UP NEW USER
        writeNewUser();
        Intent intent = new Intent(NewUserActivity.this, MainActivity.class);
        startActivity(intent);
    }


    //GET INFO
    public String getUserName() {
        return name;
    }

    public boolean isAdmin() {
        return admin;
    }
}
