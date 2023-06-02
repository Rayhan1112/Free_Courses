package com.freecourses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;


public class SplashScreen extends AppCompatActivity {
    TextView t;
    View log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        t =findViewById(R.id.splashtext);
        log=findViewById(R.id.logo);
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(SplashScreen.this, Signup.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        thread.start();

        Animation myanimation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.fade_in);
        t.startAnimation(myanimation);
        Animation myanimation1 = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.animatio2);
        log.startAnimation(myanimation1);

        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Circle();
        progressBar.setIndeterminateDrawable(doubleBounce);


    }


}
