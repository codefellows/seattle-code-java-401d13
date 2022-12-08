package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.amplifyframework.datastore.generated.model.SuperPetTypeEnum;
import com.zork.zorkmaster.R;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class AddASuperPetActivity extends AppCompatActivity {
  public final static String TAG = "AddASuperPetActivity";
  Spinner superPetTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
        superPetTypeSpinner = findViewById(R.id.AddASuperPetSpinnerType);
        setupTypeSpinner();
        setupSaveBttn();
    }

    public void setupTypeSpinner(){
      superPetTypeSpinner.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.simple_spinner_item,
        SuperPetTypeEnum.values()
      ));
    }

  public void setupSaveBttn(){
    Button saveBttn = findViewById(R.id.AddASuperPetSaveBttn);
    saveBttn.setOnClickListener(v -> {
      // TODO Builder pattern
      SuperPet newSuperPet = SuperPet.builder()
        .name(((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString())
        .type((SuperPetTypeEnum)superPetTypeSpinner.getSelectedItem())
        .birthDate(new Temporal.DateTime(new Date(), 0))
        .height(Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString()))
        .build();

      Amplify.API.mutate(
        ModelMutation.create(newSuperPet),
        success -> Log.i(TAG, "AddASuperPetActivity.onCreate(): made a super pet successfully!"),
        failure -> Log.w(TAG, "AddASuperPetActivity.onCreate(): failed to make a super pet", failure)
      );
      Toast.makeText(this, "SuperPet Saved!", Toast.LENGTH_SHORT).show();
    });
  }
}
