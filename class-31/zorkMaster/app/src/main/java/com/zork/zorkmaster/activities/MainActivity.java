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
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.auth.SignUpActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
  public static final String TAG = "mainActivity";
  public static final String DATABASE_NAME = "zork_master_db";
  public static final String ZORK_PRODUCT_EXTRA_TAG = "zorkProduct";
  public AuthUser authUser = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    authUser = Amplify.Auth.getCurrentUser();
    //TODO 2-1 Hardcode signup, verify and login
//    Amplify.Auth.signUp("alex.white@codefellows.com",
//      "p@ssw0rd",  // Cognito's default password policy is 8 characters, no other requirements
//      AuthSignUpOptions.builder()
//        .userAttribute(AuthUserAttributeKey.email(), "alex.white@codefellows")
//        .userAttribute(AuthUserAttributeKey.nickname(), "Firefly")
//        .build(),
//      success -> Log.i(TAG, "Signup suceeded" + success.toString()),
//      failure -> Log.w(TAG, "Signup dailed with email: " + "alex.white@codefellows" + "with the message: " + failure)
//    );

    // Verfication
//      Amplify.Auth.confirmSignUp(
//        "alex.white@codefellows.com",
//        "578232",
//        success -> Log.i(TAG, "verify suceeded"),
//        failure -> Log.i(TAG, "verification failed: " + failure)
//      );
//
//    //Login
//
//    Amplify.Auth.signIn(
//      "alex.white@codefellows.com",
//      "p@ssw0rd",
//      success -> Log.i(TAG, "SignIn success!"),
//      failure -> Log.e(TAG, "SignIn failed")
//    );

    // Signout -> logout
//      Amplify.Auth.signOut(
//        () ->
//        {
//          Log.i(TAG, "Logout succeeded!");
//        },
//        failure ->
//        {
//          Log.i(TAG, "Logout failed: " + failure);
//        }
//      );



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

