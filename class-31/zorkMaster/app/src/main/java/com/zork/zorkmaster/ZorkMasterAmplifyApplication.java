package com.zork.zorkmaster;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;

public class ZorkMasterAmplifyApplication extends Application {
  public final static String TAG = "zorkmasteramplifyapplication";

  @Override
  public void onCreate() {
    super.onCreate();
    try {
      Amplify.addPlugin(new AWSApiPlugin());
      Amplify.addPlugin(new AWSCognitoAuthPlugin());
      Amplify.configure(getApplicationContext());
    } catch (AmplifyException ae) {
      Log.e(TAG, "Error Initializing Amplify: " + ae.getMessage(), ae);
    }
  }
}
