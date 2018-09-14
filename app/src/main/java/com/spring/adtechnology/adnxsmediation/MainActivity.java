package com.spring.adtechnology.adnxsmediation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.FrameLayout;

import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdRegistration; //für amazon
import com.amazon.device.ads.DTBAdCallback;
import com.amazon.device.ads.DTBAdRequest;
import com.amazon.device.ads.DTBAdResponse;
import com.amazon.device.ads.DTBAdSize;
import com.appnexus.opensdk.*;
import com.appnexus.opensdk.R.*;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    //used in different functions, represents a single ad slot
    //private BannerAdView bav;
    private MediatedBannerAdView bav;
    public InterstitialAdView iav;
    private DTBAdRequest loader;
    private TextView myTextView;
    private String mytext;
    private int myn=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /////////////////////////////////////Amazonconfig/////////////////////////////////////////
        AdRegistration.getInstance("7c71c64f8b454da7aa4705963ae6e40f", getApplicationContext());
        AdRegistration.enableTesting(true);
        AdRegistration.enableLogging(true);

        //Erstellung des MediatedBannerAdView:
        //amazonUUID is given by MediaImpact
        //inventoryCode is given by Adtechnology, respectively should be given by CMS
        //AdSizes are also given by MediaImpact
        this.bav = new MediatedBannerAdView(this, "63c4c4c6-1e0b-4666-b8cd-7994163e0552");
        this.bav.setInventoryCodeAndMemberID(7823, "adtechnology.axelspringer.de-app-test-mediation_index-mrec");
        this.bav.setAdSize(300, 250);
        this.bav.setShouldServePSAs(true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.my_adspot);
        layout.addView(this.bav);
        bav.loadAd();

    }

    /*
     *  ad those to the corresponding functions in your code
     */
    public void onDestroy() {
        super.onDestroy();
        //this.bav.activityOnDestroy();
    }
    public void onPause() {
        super.onPause();
        //this.bav.activityOnPause();
    }
    public void onResume() {
        super.onResume();
        //this.bav.activityOnResume();
    }
}
