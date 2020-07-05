package com.sanok.guideforyanderess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView tntCardView , gameListCardView , wallpprCardView ;
    Intent mIntent ;
    FloatingActionButton infoFab ;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tntCardView = findViewById(R.id.tntCard);
        gameListCardView = findViewById(R.id.gamelistCard);
        wallpprCardView = findViewById(R.id.wallpprCard);
        infoFab = findViewById(R.id.infoFabBtn);

        tntCardView.setOnClickListener(this);
        gameListCardView.setOnClickListener(this);
        wallpprCardView.setOnClickListener(this);
        infoFab.setOnClickListener(this);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1601739538962474/4427541112");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        initAdmobad();
    }

    private void initAdmobad() {
        //banner
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-1601739538962474/7109544771");
// TODO: Add adView to your view hierarchy.
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


        @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tntCard:
                mIntent = new Intent(MainActivity.this,TipsNTricks.class);
                startActivity(mIntent);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
               // overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;


            case R.id.gamelistCard:
                mIntent = new Intent(MainActivity.this,gameList.class);
                startActivity(mIntent);
               // overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                break;



            case R.id.wallpprCard:
                mIntent = new Intent(MainActivity.this,wallppr.class);
                startActivity(mIntent);

                //intestial ads
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                break;
            case  R.id.infoFabBtn:
                mIntent = new Intent(MainActivity.this, App_info.class );
                startActivity(mIntent);
                break;

        }
    }
}
