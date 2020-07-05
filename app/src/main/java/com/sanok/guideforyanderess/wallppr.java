package com.sanok.guideforyanderess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Collections;

public class wallppr extends AppCompatActivity {

    ViewPager2 gameWallppr_VP2 ;
    ArrayList<gameListItemPojo> gameWallpprDataList ;
    InterstitialAd interstitialAd = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallppr);


        gameWallpprDataList = new ArrayList<>();
        Log.e("TAG_ALL","ok Before Add List to ");

        for(int i = 1 ; i<12 ;i++ ){
            gameWallpprDataList
                    .add(new gameListItemPojo( getResources().getIdentifier("yss"+i, "drawable", getPackageName()) ,"",""));
        }

        Collections.shuffle(gameWallpprDataList);

        gameWallppr_VP2 = findViewById(R.id.gameWallppr_VP2);
        Log.e("TAG_ALL","OK Before FInding VP2");
        gameWallppr_VP2.setAdapter(new gameListRV_Adapter(this,gameWallpprDataList,gameListRV_Adapter.GAMEWALLPAPER_VIEWPAGER2_ITEMTYPE));

        Log.e("TAG_ALL","OK AFTER ADDING TO  ADAPTER  VP2");

        gameWallppr_VP2.setOffscreenPageLimit(2);
        gameWallppr_VP2.setClipToPadding(false);
        gameWallppr_VP2.setClipChildren(false);
        gameWallppr_VP2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer cmPgTransformer = new CompositePageTransformer();
        cmPgTransformer.addTransformer(new MarginPageTransformer(5));
        cmPgTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });

        gameWallppr_VP2.setPageTransformer(cmPgTransformer);
        initAdmobad();

        //backpress intestial
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-1601739538962474/4427541112");
        interstitialAd.loadAd(new AdRequest.Builder().build());

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
    public void onBackPressed() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    }
