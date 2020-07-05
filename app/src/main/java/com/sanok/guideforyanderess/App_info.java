package com.sanok.guideforyanderess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class App_info extends AppCompatActivity implements View.OnClickListener {

    LinearLayout shareitBtn , contectUsbtn , pPolicyBtn ,rateUsBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

//        shareitBtn = findViewById(R.id.shareItBtn);
        contectUsbtn = findViewById(R.id.contectUs_btn);
        pPolicyBtn = findViewById(R.id.privacyPolicyBtn);
        rateUsBtn = findViewById(R.id.rateusBtn);

//        shareitBtn.setOnClickListener(this);
        contectUsbtn.setOnClickListener(this);
        pPolicyBtn.setOnClickListener(this);
        rateUsBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.shareItBtn:
                break;*/
            case R.id.contectUs_btn:
                 String email =   "ehsanokhan90@gmail.com";
                Uri uri_contectUs = Uri.parse("mailto:" + email)
                        .buildUpon()
                        .build();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri_contectUs);
                startActivity(Intent.createChooser(emailIntent, "chooserTitle"));
                break;

            case R.id.privacyPolicyBtn:
                // Private Policy Link Here
               // https://sites.google.com/view/guideforhalo/home

                //short intent for privacy policy
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/guideforyandereschoolsimulator/home")));
                Toast.makeText(App_info.this, "Privacy Policy", Toast.LENGTH_SHORT).show();
                break;


            case R.id.rateusBtn:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Sano.k")));
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("market://search?q=pub:Sano.K"));
                App_info.this.startActivity(intent);
                break;

        }
    }
}
