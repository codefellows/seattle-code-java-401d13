package com.zork.zorkmaster.activities.auth;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.core.Amplify;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
  public static final String TAG = "signInActivity";
  Intent callingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        callingIntent = getIntent();

        setUpSignInForm();
    }

    public void setUpSignInForm(){
      // accept intent w/ extra from verify activity
                      // AUTO FILL
//      String userEmail = callingIntent.getStringExtra(SignUpActivity.SIGNUP_EMAIL_TAG);
//      EditText emailEditText = findViewById(R.id.signInETEmail);
//      emailEditText.setText(userEmail);
      // setup on click listener for login bttn
      findViewById(R.id.signInBttnSignIn).setOnClickListener(v -> {
      // gather email and password
        String userEmail = ((EditText) findViewById(R.id.signInETEmail)).getText().toString();
        String userPassword = ((EditText) findViewById(R.id.signInETPassword)).getText().toString();

      // make a login call to Cognito
        Amplify.Auth.signIn(
          userEmail,
          userPassword,
          success -> {
            Log.i(TAG, "Login succeeded: " + success);
            Intent goToProductListIntent = new Intent(this, MainActivity.class);
            startActivity(goToProductListIntent);
          },
          failure -> {
            Log.i(TAG, "Login failed: " + failure);
            runOnUiThread(() -> Toast.makeText(this, "Sign In failed!", Toast.LENGTH_SHORT).show());
          }
        );
      });
    }
}
