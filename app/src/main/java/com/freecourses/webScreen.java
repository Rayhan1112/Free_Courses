package com.freecourses;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

public class webScreen extends AppCompatActivity {
    WebView view;
    public ProgressBar pb;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_screen);

         pb=findViewById(R.id.pg);
        Sprite doubleBounce = new Wave();
        pb.setIndeterminateDrawable(doubleBounce);
         view=findViewById(R.id.web);
        Intent intent=getIntent();
        WebSettings webSettings=view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(false);
       view.setWebViewClient(new WebViewClient(){
           @Override
           public void onPageStarted(WebView view, String url, Bitmap favicon) {
               super.onPageStarted(view, url, favicon);
               pb.setVisibility(View.VISIBLE);
               setTitle("Loading.......");
           }

           @Override
           public void onPageFinished(WebView view, String url) {
               super.onPageFinished(view, url);
               pb.setVisibility(View.GONE);
               setTitle(view.getTitle());
           }
       });
        String weblink=intent.getStringExtra("links");
        view.loadUrl(weblink);

    }


    @Override
    public void onBackPressed() {
        if(view.canGoBack()){
            view.goBack();
        }else{
            super.onBackPressed();
        }

    }

}