package com.example.owner.giveback;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.owner.giveback.adapter.ViewPagerAdapter;

public class NewUserActivity extends AppCompatActivity {

    ViewPager mPager;
    ProgressBar progress;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

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
