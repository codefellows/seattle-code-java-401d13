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
  public static final String ZORK_PRODUCT_EXTRA_TAG = "zorkProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBttns();
        setupUserProfileImageButton();
    }

    @Override
    protected void onResume() { super.onResume();
      setupGreeting();
      setupProductButtons();
      setupSuperPetBttn();

    }

    public void setupBttns(){
      Button goToOrderFormBttn = MainActivity.this.findViewById(R.id.MainActivityOrderFormBttn);
      // setting up routing logic with intents. Intents are the highway between activities
      goToOrderFormBttn.setOnClickListener(view -> {
        // set up the intent (Current context.this, class to go to Class.class)
        Intent goToOrderFormActivity = new Intent(this, OrderForm.class);
        // launch the intent
        startActivity(goToOrderFormActivity);
      });
    }

    public void setupUserProfileImageButton(){
      ImageView userProfileLink = MainActivity.this.findViewById(R.id.MainActivityImageViewUserProfile);
      userProfileLink.setOnClickListener(v -> {
        Intent goToUserProfile = new Intent(this, UserProfileActivity.class);
        startActivity(goToUserProfile);
      });
    }

    public void setupGreeting(){
      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
      String username = preferences.getString(UserProfileActivity.USERNAME_TAG, "No username");
      ((TextView)findViewById(R.id.MainActivityTextViewGreeting)).setText(username);
    }

    public void setupProductButtons(){

      Button bullyBttn = findViewById(R.id.MainActivityBullyBttn);
      Intent goToOrderFormIntent = new Intent(this, OrderForm.class);

      bullyBttn.setOnClickListener(v -> {
        goToOrderFormIntent.putExtra(ZORK_PRODUCT_EXTRA_TAG, bullyBttn.getText().toString());
        startActivity(goToOrderFormIntent);
      });
      }

      public void setupSuperPetBttn() {
        Button superPetBtn = findViewById(R.id.MainActivitySuperPetBttn);
        Intent goToSuperPet = new Intent(this, SuperPetActivity.class);
        superPetBtn.setOnClickListener(v -> {
          startActivity(goToSuperPet);
        });
      }

      //find the UI bttns
      // create an intent to ORderForm and include the product
      //

    }

