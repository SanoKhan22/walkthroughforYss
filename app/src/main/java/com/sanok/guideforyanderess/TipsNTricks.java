package com.sanok.guideforyanderess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;

public class TipsNTricks extends AppCompatActivity {

    WebView tipsWebView;
    String tipsHtmlfileURL ;
    private AdView adView;//fb ads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_n_tricks);
        AudienceNetworkAds.initialize(this);

        tipsWebView = findViewById(R.id.tntWebView);
        tipsHtmlfileURL = "file:///android_asset/tipsyand.html";
        tipsWebView.getSettings().setJavaScriptEnabled(true);
        tipsWebView.loadUrl(tipsHtmlfileURL);

        adView = new AdView(this, "299014428145676_299015074812278", AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();

    }
}
