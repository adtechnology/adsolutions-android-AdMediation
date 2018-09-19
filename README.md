# adsolutions-android-AdMediation
Sample Project to showcase Appnexus Mediation and Amazon Prebidding. 
The projects also provides the class "MediatedBannerAdView" combining Amazon Prebidding and Appnexus' BannerAdview

needed libs:
Appnexus SDK                      com.appnexus.opensdk:appnexus-sdk
Google Ad SDK                     com.google.android.gms:play-services-ads
Amazon SDK                        Download DTBAndroidSDK
Appnexus SDK ADNXS-Google-Bridge  com.appnexus.opensdk.mediatedviews:appnexus-googleplay-mediation 


Appnexus docs
https://wiki.appnexus.com/display/sdk/Show+Banners

Data/Parameter:
Amazon Appkey:                --> provided by Adtechnology/Media Impact Programmatic
Amazon UUID:                  --> provided by Adtechnology/Media Impact Programmatic
inventoryCode/Placementcode:  --> provided by Adtechnology/possibly CMS (like e.g. bild.de-app_android_phone-auto_index-banner)
AdSizes                       --> provided by Adtechnology

# Todo
When initializing app:
  AdRegistration.getInstance(AMAZON_APPKEY, getApplicationContext());

When implementing a Bannerslot, e.g.:
  bav = new MediatedBannerAdView(this, "AMAZON_UUID");
  bav.setInventoryCodeAndMemberID(7823, "PLACEMENTCODE");
  bav.setAdSize(300, 250); //Size 300+250
  layout.addView(this.bav);
  bav.loadAd(); //to initialize Bannerload and Rendering
