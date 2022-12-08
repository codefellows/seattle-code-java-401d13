package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zork.zorkmaster.R;

public class MainActivity extends AppCompatActivity {
  public static final String DATABASE_NAME = "zork_master_db";
  public static final String ZORK_PRODUCT_EXTRA_TAG = "zorkProduct";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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
    Button goToOrderFormBttn = MainActivity.this.findViewById(R.id.MainActivityOrderFormBttn);
    goToOrderFormBttn.setOnClickListener(view -> {
      Intent goToOrderFormActivity = new Intent(this, OrderForm.class);
      startActivity(goToOrderFormActivity);
    });
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

