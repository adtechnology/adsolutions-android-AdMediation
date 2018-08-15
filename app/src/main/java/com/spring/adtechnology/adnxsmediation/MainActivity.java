package com.spring.adtechnology.adnxsmediation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.FrameLayout;
import com.appnexus.opensdk.*;
import com.appnexus.opensdk.R.*;
import android.os.Handler;
import android.widget.LinearLayout;
//import com.appnexus.opensdk.mediatedviews:appnexus-googleplay-mediation:[4,5)


public class MainActivity extends AppCompatActivity {

    //used in different functions, represents a single ad slot
    private BannerAdView bav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
