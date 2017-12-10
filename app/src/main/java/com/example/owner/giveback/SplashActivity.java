package com.example.owner.giveback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import java.util.Timer;
import java.util.TimerTask;
import android.view.animation.Animation;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {

    private Timer timerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timerMain = new Timer();
        timerMain.schedule(new MyTimerTask(), 3000);

        final ImageView ivLogo = (ImageView) findViewById(R.id.ivLogo);
        final Animation sendAnim = AnimationUtils.loadAnimation(this,
                R.anim.splash_anim);

        sendAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                ivLogo,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        scaleDown.setDuration(310);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();

        ivLogo.startAnimation(sendAnim);

    }


    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            final Intent intentLogin = new Intent();
            intentLogin.setClass(SplashActivity.this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }
    }
}
