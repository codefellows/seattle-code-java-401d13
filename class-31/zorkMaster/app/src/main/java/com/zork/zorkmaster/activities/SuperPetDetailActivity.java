package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.zork.zorkmaster.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SuperPetDetailActivity extends AppCompatActivity {
  public static final String TAG = "super_pet_detail_activity";
  Intent callingIntent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_super_pet_detail);
  }

  @Override
  protected void onResume() {
    super.onResume();
    callingIntent = getIntent();

  }

  // setup Super Pet details
  private void setupSuperPetDetails(){
    String superPetId = null;
    TextView superPetNameTextView = findViewById(R.id.SuperPetDetailTextViewName);
    TextView superPetTypeTextView = findViewById(R.id.SuperPetDetailTextViewType);
    TextView superPetHeightTextView;
    if(callingIntent != null){
    superPetId = callingIntent.getStringExtra(SuperPetActivity.SUPER_PET_ID_TAG);
      SuperPet superPet;
    } else {
      superPetNameTextView.setText("No Super Pet selected");
    }
  }

  private void getSuperPetById(String superPetId){
    SuperPet databaseSuperPet;
    Amplify.API.query(
      ModelQuery.get(SuperPet.class, superPetId),
      success -> {
        Log.i(TAG, "Query Super Pet correctly");
      },
      // TODO: finish implementing the details call
      failure -> {}
    );
  }

  // setup Super Pet Image
  private void setupSuperPetImage(){

  }



}
