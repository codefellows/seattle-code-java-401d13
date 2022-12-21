package com.zork.zorkmaster.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.zork.zorkmaster.R;

public class OrderForm extends AppCompatActivity {
  private static final String TAG = "OrderFormAds";
  private AdView mAdView;
  private InterstitialAd mInterstitialAd;
  private RewardedAd mRewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        consumeProductExtra();
        setupAds();
    }


    public void consumeProductExtra(){
      Intent callingIntent = getIntent();
      String productName = null;
      if(callingIntent != null){
        productName = callingIntent.getStringExtra(MainActivity.ZORK_PRODUCT_EXTRA_TAG);
      }
      TextView orderFormProductView = findViewById(R.id.OrderFormTVProduct);
      if(productName != null) {
        orderFormProductView.setText(productName);
      }
      else {
        orderFormProductView.setText("No product");
      }
    }

    private void setupAds(){
      MobileAds.initialize(this, initializationStatus -> {
      });
//      mAdView = findViewById(R.id.adView);
      AdRequest adRequest = new AdRequest.Builder().build();
//      mAdView.loadAd(adRequest);

//      InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
//        new InterstitialAdLoadCallback() {
//          @Override
//          public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//            // The mInterstitialAd reference will be null until
//            // an ad is loaded.
//            mInterstitialAd = interstitialAd;
//            Log.i(TAG, "onAdLoaded");
//            mInterstitialAd.show(OrderForm.this);
//          }
//
//          @Override
//          public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//            // Handle the error
//            Log.d(TAG, loadAdError.toString());
//            mInterstitialAd = null;
//          }
//        });


      RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
        adRequest, new RewardedAdLoadCallback() {
          @Override
          public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
            // Handle the error.
            Log.d(TAG, loadAdError.toString());
            mRewardedAd = null;
          }

          @Override
          public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
            mRewardedAd = rewardedAd;
            Log.d(TAG, "Ad was loaded.");
            Activity activityContext = OrderForm.this;
            mRewardedAd.show(activityContext, rewardItem -> {
              // Handle the reward.
              Log.d(TAG, "The user earned the reward.");
              int rewardAmount = rewardItem.getAmount();
              String rewardType = rewardItem.getType();
            });
          }
        });

    }
}
