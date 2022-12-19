package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = "mainActivity";
  public static final String DATABASE_NAME = "zork_master_db";
  public static final String ZORK_PRODUCT_EXTRA_TAG = "zorkProduct";
  public AuthUser authUser = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // TODO This is how you get the currentAuthUser
    Amplify.Auth.getCurrentUser(
      success -> {
        Log.i(TAG, "There is a user");
        authUser = success;
        },
      failure -> {
        Log.w(TAG, "There is no current authenticated User");
        authUser = null;
      }
    );

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

