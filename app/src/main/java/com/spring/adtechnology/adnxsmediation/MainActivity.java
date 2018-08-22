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
//import com.appnexus.opensdk.mediatedviews:appnexus-googleplay-mediation:[4,5)


public class MainActivity extends AppCompatActivity {

    //used in different functions, represents a single ad slot
    private BannerAdView bav;
    private DTBAdRequest loader;
    private TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = (TextView) findViewById(R.id.mytext);
        myTextView.setText("My Awesome Text");
        AdRegistration.getInstance("7c71c64f8b454da7aa4705963ae6e40f", getApplicationContext());

        AdRegistration.enableTesting(true);
        AdRegistration.enableLogging(true);

        loader = new DTBAdRequest();
        loader.setSizes(new DTBAdSize(300, 250,"63c4c4c6-1e0b-4666-b8cd-7994163e0552"));
        loader.loadAd(new DTBAdCallback() {
            @Override
            public void onFailure(AdError adError) {
                Log.e("AdError", "Oops banner ad load has failed: " + adError.getMessage());
                /**Please implement the logic to send ad request without our parameters if you want to
                 show ads from other ad networks when Amazon ad request fails**/
                myTextView.setText("My Awesome Failure"+ adError.getMessage());

            }

            @Override
            public void onSuccess(DTBAdResponse dtbAdResponse) {
                Map<String, List<String>> custParams = dtbAdResponse.getDefaultDisplayAdsRequestCustomParams();
                Log.e("AdError", "Oops banner ad loaded" );
                myTextView.setText(custParams.toString());
                //bav.addCustomKeywords("test", "true");
                //Loop through custParams and forward the targeting to your ad server
            }
        });

        //Adnx Placement initialisieren
        this.bav = new BannerAdView(this);
        this.bav.setInventoryCodeAndMemberID(7823, "adtechnology.axelspringer.de-app-test-mediation_index-mrec");
        bav.addCustomKeywords("test", "true");
        this.bav.setAdSize(300, 250);
        this.bav.setShouldServePSAs(true);

        LinearLayout layout = (LinearLayout) findViewById(R.id.my_adspot);
        layout.addView(this.bav);
    }

    /*
     *  ad those to the corresponding functions in your code
     */
    public void onDestroy() {
        super.onDestroy();
        this.bav.activityOnDestroy();
    }
    public void onPause() {
        super.onPause();
        this.bav.activityOnPause();
    }
    public void onResume() {
        super.onResume();
        this.bav.activityOnResume();
    }
}
