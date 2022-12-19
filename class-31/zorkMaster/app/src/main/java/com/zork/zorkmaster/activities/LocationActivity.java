package com.zork.zorkmaster.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.zork.zorkmaster.R;

import java.io.IOException;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {
  private static final String TAG = "location_activity";
  //TODO: Step 1-1 Add gradle dependency and request perissions in AndroidManifest
  //TODO: Step 1-2 setup fusedLocationProviderClient
  private FusedLocationProviderClient fusedLocationProviderClient;
  private Geocoder geocoder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    //TODO: Step 1-2 setup fusedLocationProviderClient
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    geocoder = new Geocoder(this, Locale.getDefault());
//    getLocations();
    getLocationUpdates();
  }

  private void getLocations() {
    // TODO 1-3 implement getLastLocation
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      return;
    }
    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
      if (location == null) {
        Log.w(TAG, "Location was null");
      }
      String lastLatitude = Double.toString(location.getLatitude()); // 34.0000000067
      String lastLongitude = Double.toString(location.getLongitude()); // 236.000959
      Log.i(TAG, "Our last latitude " + lastLatitude);
      Log.i(TAG, "Our last Longitude " + lastLongitude);
    });

    // TODO 1-4 implement getCurrentLocation -> more update and accurate
    fusedLocationProviderClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, new CancellationToken() {
      @NonNull
      @Override
      public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
        return null;
      }

      @Override
      public boolean isCancellationRequested() {
        return false;
      }
    }).addOnSuccessListener(location -> {
      if (location == null) {
        Log.e(TAG, "Location callback was null!");
      }
      Log.i(TAG, "Our latitude: " + location.getLatitude());
      Log.i(TAG, "Our longitude: " + location.getLongitude());
    });

  }

  private void getLocationUpdates() {
    //TODO Step 1-5 implement requestLocationUpdates with our Geocoder
    LocationRequest locationRequest = LocationRequest.create();
    locationRequest.setInterval(5 * 1000);
    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    LocationCallback locationCallback = new LocationCallback() {
      @Override
      public void onLocationResult(@NonNull LocationResult locationResult) {
        super.onLocationResult(locationResult);
        try {
          String address = geocoder.getFromLocation(
            locationResult.getLastLocation().getLatitude(),
            locationResult.getLastLocation().getLongitude(),
            1) // 1 -> give the best result
            .get(0) // the best address given
            .getAddressLine(0); // first line of the address
          Log.i(TAG, "Repeating current location is: " + address);
        } catch (IOException ioe) {
          Log.e(TAG, "Could not get subscribed location: " + ioe.getMessage(), ioe);
        }
      }
    };
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      // TODO: Consider calling
      //    ActivityCompat#requestPermissions
      // here to request the missing permissions, and then overriding
      //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
      //                                          int[] grantResults)
      // to handle the case where the user grants the permission. See the documentation
      // for ActivityCompat#requestPermissions for more details.
      return;
    }
    fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, getMainLooper());
  }


}
