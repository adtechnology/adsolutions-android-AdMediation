package com.spring.adtechnology.adnxsmediation;

import android.content.Context;
import android.util.Log;

import com.amazon.device.ads.AdError;
import com.amazon.device.ads.DTBAdCallback;
import com.amazon.device.ads.DTBAdRequest;
import com.amazon.device.ads.DTBAdResponse;
import com.amazon.device.ads.DTBAdSize;
import com.appnexus.opensdk.AdSize;
import com.appnexus.opensdk.BannerAdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
 * MediatedBannerAdView extends BannerAdView with Amazon "PreBidding"
 * -amazonUUID needed
 * -loadAD() first calls prebid Amazon, then sets response as Keywords and than calls Adnxs
 */
public class MediatedBannerAdView extends BannerAdView {
    private DTBAdRequest loader;
    private String azUuid;

    public MediatedBannerAdView(Context context, String amazonUUID) {
        super(context);
        this.setAmazonUUID(amazonUUID);
        this.setAutoRefreshInterval(0);
    }
    public boolean loadANAd(){
        return super.loadAd();
    }
    @Override
    public boolean loadAd() {
        MediatedBannerAdView that = this;
        loader.loadAd(new DTBAdCallback() {
            @Override
            public void onFailure(AdError adError) {
                Log.e("Adnxs", "-----------------------------------------------------Amazon.onFailure----------------------------------------------");
                Log.e("AdError", "Oops banner ad load has failed: " + adError.getMessage());
              //alte Amazon Keys entfernen!
                that.removeCustomKeyword("amzn_b");
                that.removeCustomKeyword("amzn_h");
                that.removeCustomKeyword("amznslots");
                that.removeCustomKeyword("amznp");

                that.loadANAd();
            }

            @Override
            public void onSuccess(DTBAdResponse dtbAdResponse) {
                Map<String, List<String>> custParams = dtbAdResponse.getDefaultDisplayAdsRequestCustomParams();

                Log.e("Adnxs", "-----------------------------------------------------Amazon.onSuccess----------------------------------------------");

                //alte Amazon Keys entfernen!
                that.removeCustomKeyword("amzn_b");
                that.removeCustomKeyword("amzn_h");
                that.removeCustomKeyword("amznslots");
                that.removeCustomKeyword("amznp");

                //Loop through custParams and forward the targeting to your ad server
                for (Map.Entry<String, List<String>> entry : custParams.entrySet()) {
                    Log.e("Adnxs",entry.getKey() + "/" + entry.getValue().get(0));
                    that.addCustomKeywords(entry.getKey(), entry.getValue().get(0));
                }
                that.loadANAd();
            }
        });
        return true;
    }
    /*
     *  if not set, there will be no "Amazon Headerbidding", so no Amazon Keywords will be transmitted
     */
    public boolean setAmazonUUID(String uuid){

        this.loader = new DTBAdRequest();
        this.azUuid = uuid;
        //sobald muuid gesetzt alle bisherigen ADNXS Sizes auch als Amazon Adsizes anlegen
        this.setAmazonSizes();
        return true;
    }
    /*
     * aus allen ADNXS Sizes auch Amazon Sizes machen
     */
    private boolean setAmazonSizes(){
        if (loader != null) {
            DTBAdSize[] sizes = new DTBAdSize[this.getAdSizes().size()];
            for (int i = 0; i < this.getAdSizes().size(); i++) {
                AdSize adsize = this.getAdSizes().get(i);
                sizes[i] = new DTBAdSize(adsize.width(), adsize.height(), this.azUuid);
            }
            loader.setSizes(sizes);
            return true;
        }
        return loadANAd();
    }
    @Override
    public void setAdSize(int w, int h) {
        super.setAdSize(w, h);
        this.setAmazonSizes();
    }

    @Override
    public void setAdSizes(ArrayList<AdSize> adSizes) {
        super.setAdSizes(adSizes);
        this.setAmazonSizes();
    }

}
