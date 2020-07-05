package com.sanok.guideforyanderess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class gameList extends AppCompatActivity {

        RecyclerView gameListRecyclerView ;
        ArrayList<gameListItemPojo> gameDataArrayList ;
    InterstitialAd interstitialAd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        gameDataArrayList = new ArrayList<>();

        gameDataArrayList.add(new gameListItemPojo(R.drawable.studentyandere_kun, "Yandere Kun" , "Student"));
        gameDataArrayList.add(new gameListItemPojo(R.drawable.carrercriminal_yakuza, "Yakuza" , "Carrer Criminal"));
        gameDataArrayList.add(new gameListItemPojo(R.drawable.femalestudent, "Yandere Chan" , "Student"));
        gameDataArrayList.add(new gameListItemPojo(R.drawable.headmaster, "Kocho Shuyona" , "Head Master"));
        gameDataArrayList.add(new gameListItemPojo(R.drawable.momlove, "Ryoba Aishi" , "Loving Mother"));
        gameDataArrayList.add(new gameListItemPojo(R.drawable.musicleader, "Miyuji Shan" , "Music Leader"));


        gameListRecyclerView = findViewById(R.id.gameListRecyclerView);
        gameListRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        gameListRecyclerView.setAdapter(new gameListRV_Adapter(this,gameDataArrayList,gameListRV_Adapter.GAMELIST_RECYCLERVIEW_ITEMTYPE));

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

        //backpress intestial
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-1601739538962474/4427541112");
        interstitialAd.loadAd(new AdRequest.Builder().build());
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
        }else{
            super.onBackPressed();
        }

    }


}
