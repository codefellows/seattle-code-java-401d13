package com.zork.zorkmaster.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;

public class SignUpActivity extends AppCompatActivity {
  public static final String TAG = "SignUpActivity";
  public static final String SIGNUP_EMAIL_TAG = "Signup_Email_Tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setUpSignUpForm();
    }


  public void setUpSignUpForm(){
    // gather all the info: email and password
    // call to Cognito to create a new user onClick
    findViewById(R.id.signUpbttnSignUp).setOnClickListener(v -> {
      String userEmail = ((EditText) findViewById(R.id.signUpETEmail)).getText().toString();
      String userPassword = ((EditText) findViewById(R.id.signUpETPassword)).getText().toString();
      Amplify.Auth.signUp(
        userEmail,
        userPassword,
        AuthSignUpOptions.builder()
          .userAttribute(AuthUserAttributeKey.email(), userEmail)
          .build(),
        success -> {
          Log.i(TAG, "SignUp success! " + success);
          // send them to the verify activity
          Intent goToVerifyActivity = new Intent(this, VerifySignUpActivity.class);
          // put an extra in the intent -> user email. Auto fill
          goToVerifyActivity.putExtra(SIGNUP_EMAIL_TAG, userEmail);
          startActivity(goToVerifyActivity);
        },
        failure -> {
          Log.w(TAG, "Sign up failed with username: " + userEmail + "with message " + failure);
          runOnUiThread(() -> Toast.makeText(this, "SignUp Failed", Toast.LENGTH_SHORT).show());
        }
      );
    });


  }


}
