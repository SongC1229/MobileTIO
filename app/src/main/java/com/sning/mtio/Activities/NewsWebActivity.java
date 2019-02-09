package com.sning.mtio.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import com.sning.mtio.R;

public class NewsWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        setContentView(R.layout.activity_news_web);
        WebView webView = findViewById(R.id.z_web_view);
        webView.loadUrl(url);
    }
}
