package com.example.owner.giveback;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.owner.giveback.adapter.ViewPagerAdapter;

public class NewUserActivity extends AppCompatActivity {

    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        mPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);
    }


    public void jumpToPage(View view) {
        mPager.setCurrentItem(1);
    }

    public void jumpToPage2(View view) {
        mPager.setCurrentItem(2);
    }

    public void jumpToPageDone(View view) {
        Intent intent = new Intent(NewUserActivity.this, LandingActivity.class);
        startActivity(intent);
    }
}
