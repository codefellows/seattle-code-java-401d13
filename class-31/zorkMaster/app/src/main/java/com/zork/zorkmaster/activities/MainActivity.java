package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.analytics.AnalyticsEvent;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = "mainActivity";
  public static final String DATABASE_NAME = "zork_master_db";
  public static final String ZORK_PRODUCT_EXTRA_TAG = "zorkProduct";
  public AuthUser authUser = null;
  private final MediaPlayer mp = new MediaPlayer();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      Amplify.Predictions.convertTextToSpeech(
        "I like to eat spaghetti",
        result -> playAudio(result.getAudioData()),
        error -> Log.e("MyAmplifyApp", "Conversion failed", error)
      );

    AnalyticsEvent event = AnalyticsEvent.builder()
      .name("Opened App")
      .addProperty("Time", Long.toString(new Date().getTime()))
      .addProperty("trackingEvent", "Main activity was opened")
      .build();

    Amplify.Analytics.recordEvent(event);

    setupBttns();
    setupUserProfileImageButton();
    }


  @Override
  protected void onResume() {
    super.onResume();
    setupGreeting();
    setupProductButtons();
    setupSuperPetBttn();

  }

  private void playAudio(InputStream data) {
    File mp3File = new File(getCacheDir(), "audio.mp3");

    try (OutputStream out = new FileOutputStream(mp3File)) {
      Log.i(TAG, "Reading input stream");
      byte[] buffer = new byte[8 * 1_024];
      int bytesRead;
      while ((bytesRead = data.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }
      mp.reset();
      mp.setOnPreparedListener(MediaPlayer::start);
      mp.setDataSource(new FileInputStream(mp3File).getFD());
      mp.prepareAsync();
    } catch (IOException error) {
      Log.e("MyAmplifyApp", "Error writing audio file", error);
    }

  }

  @Override
  protected void onPause() {
    super.onPause();
    AnalyticsEvent event = AnalyticsEvent.builder()
      .name("Paused main activity")
      .addProperty("Time", Long.toString(new Date().getTime()))
      .addProperty("trackingEvent", "Main activity was paused")
      .build();

    Amplify.Analytics.recordEvent(event);
  }

  public void setupBttns() {
    Button goToLocationActivityBttn = MainActivity.this.findViewById(R.id.MainActivityLocationBttn);
    goToLocationActivityBttn.setOnClickListener(view -> {
      Intent goToLocationActivity = new Intent(this, LocationActivity.class);
      startActivity(goToLocationActivity);
    });
    // Sign in button
    Button signIn = findViewById(R.id.MainActivitySignInBttn);
    // sign up button
    Button signUp = findViewById(R.id.MainActivitySignUpBttn);


    // get current authenticted user
    // if user is null -> show signUp button, hide signIn button
    if (authUser == null){
      // not signed in: see sign up sign in hide logout
      signIn.setVisibility(View.VISIBLE);
      signUp.setVisibility(View.VISIBLE);
    } else {
      String username = authUser.getUsername();
      Log.i(TAG, "Username is: " + username);
      // signed in. hhide sign up and sign in and show logout
      signIn.setVisibility(View.INVISIBLE);
      signUp.setVisibility(View.INVISIBLE);

    }
  }

  public void setupUserProfileImageButton() {
    ImageView userProfileLink = MainActivity.this.findViewById(R.id.MainActivityImageViewUserProfile);
    userProfileLink.setOnClickListener(v -> {
      Intent goToUserProfile = new Intent(this, UserProfileActivity.class);
      startActivity(goToUserProfile);
    });
  }

  public void setupGreeting() {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    String username = preferences.getString(UserProfileActivity.USERNAME_TAG, "No username");
    ((TextView) findViewById(R.id.MainActivityTextViewGreeting)).setText(username);
  }

  public void setupProductButtons() {

    Button addASuperPetBttn = findViewById(R.id.MainActivityAddASuperPetBttn);
    Intent goToAddASuperPetIntent = new Intent(this, AddASuperPetActivity.class);

    addASuperPetBttn.setOnClickListener(v -> {
      goToAddASuperPetIntent.putExtra(ZORK_PRODUCT_EXTRA_TAG, addASuperPetBttn.getText().toString());
      startActivity(goToAddASuperPetIntent);
    });
  }

  public void setupSuperPetBttn() {
    Button superPetBtn = findViewById(R.id.MainActivitySuperPetBttn);
    Intent goToSuperPet = new Intent(this, SuperPetActivity.class);
    superPetBtn.setOnClickListener(v -> {
      startActivity(goToSuperPet);
    });
  }

}

