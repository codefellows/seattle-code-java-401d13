package com.zork.zorkmaster.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;

public class VerifySignUpActivity extends AppCompatActivity {
  public static final String TAG = "VerifyAccountActivity";
  Intent callingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_sign_up);
        callingIntent = getIntent();

        setUpVerifyForm();
    }

    public void setUpVerifyForm(){
      // accept extra from calling activity
      // gather info -> email & verification code
      String userEmail = callingIntent.getStringExtra(SignUpActivity.SIGNUP_EMAIL_TAG);
      findViewById(R.id.verifyBttnVerify).setOnClickListener(v -> {
        String verificationCode = ((EditText) findViewById(R.id.verifyETConfirmationCode)).getText().toString();
      //verify call to Cognito
        Amplify.Auth.confirmSignUp(
          userEmail,
          verificationCode,
          success -> {
            Log.i(TAG, "Verification succeeded: " + success.toString());
            Intent goToSignInActivity = new Intent(this, SignInActivity.class);
            goToSignInActivity.putExtra(SignUpActivity.SIGNUP_EMAIL_TAG, userEmail);
            startActivity(goToSignInActivity);
          },
          failure -> {
            Log.i(TAG, "Verification failed with username: " + userEmail + " with this message: " + failure);
            runOnUiThread(() -> Toast.makeText(VerifySignUpActivity.this, "Verify account failed!", Toast.LENGTH_SHORT));
          }
        );
      });

    }
}
