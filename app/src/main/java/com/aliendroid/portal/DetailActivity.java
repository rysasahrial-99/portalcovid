package com.aliendroid.portal;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.covid17.R;


public class DetailActivity extends AppCompatActivity {
    private int position = 0;
    ProgressBar progressBar;
    WebView webView;
    private ImageView postImage;



    @Override
    public void onSaveInstanceState(Bundle saveInsBundleState) {
        super.onSaveInstanceState(saveInsBundleState);
        saveInsBundleState.putInt("position", position);

    }
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle saveInsBundleState) {
        super.onCreate(saveInsBundleState);
        setContentView(R.layout.activity_detail);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = extras.getInt("position");
        } else {
            if (saveInsBundleState != null) {
                position = saveInsBundleState.getInt("position");
            } else {
                position = 1;
            }
        }

        webView = findViewById(R.id.detailview);
        postImage= findViewById(R.id.imageView);



        TextView textView = (TextView) findViewById(R.id.txt_judul);
        textView.setText(PostAdapter.items.get(position).getTitle());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //textView.setText(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
                textView.setText(webView.getTitle());

            }
        });
        webView.loadUrl(PostAdapter.items.get(position).getUrl());

    }




    @Override
    public void onBackPressed()
    {
        /*Intent intent=new Intent(getApplicationContext(), ExitActivity.class);
        startActivity(intent);

         */
        super.onBackPressed();
    }



}
