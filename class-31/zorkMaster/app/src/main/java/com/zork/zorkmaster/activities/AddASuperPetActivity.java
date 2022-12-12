package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.SuperOwner;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddASuperPetActivity extends AppCompatActivity {
  public final static String TAG = "AddASuperPetActivity";
  Spinner superPetTypeSpinner;
  // Todo Step: 1-3 setup owner spinner
  Spinner superOwnerSpinner;
  // Todo Step: 1-4 implement CompleteableFuture
  CompletableFuture<List<SuperOwner>> superOwnersFuture = new CompletableFuture<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
      // Todo Step: 1-2 hardcode/add 3 SuperOwners to AWS db
//      SuperOwner newSuperOwner1 = SuperOwner.builder()
//        .name("Joe")
//        .email("Joe@joe.joe")
//        .build();
//      SuperOwner newSuperOwner2 = SuperOwner.builder()
//        .name("Mat")
//        .email("Mat@mat.mat")
//        .build();
//      SuperOwner newSuperOwner3 = SuperOwner.builder()
//        .name("Max")
//        .email("Max@max.max")
//        .build();
//      Amplify.API.mutate(
//        ModelMutation.create(newSuperOwner1),
//        success -> {},
//        failure -> {}
//      );
//      Amplify.API.mutate(
//        ModelMutation.create(newSuperOwner2),
//        success -> {},
//        failure -> {}
//      );
//      Amplify.API.mutate(
//        ModelMutation.create(newSuperOwner3),
//        success -> {},
//        failure -> {}
//      );

        superPetTypeSpinner = findViewById(R.id.AddASuperPetSpinnerType);
      // Todo Step: 1-3 setup owner spinner
        superOwnerSpinner = findViewById(R.id.AddASuperPetSpinnerSuperOwner);

    // TODO query and setup a spinner for super owners
      // Compeleteable Future
      Amplify.API.query(
        ModelQuery.list(SuperOwner.class),
        success -> {
          Log.i(TAG, "Read Super Owners Successfully");
          ArrayList<String> ownerNames = new ArrayList<>();
          ArrayList<SuperOwner> superOwners = new ArrayList<>();
          for (SuperOwner superOwner: success.getData()) {
            ownerNames.add(superOwner.getName());
            superOwners.add(superOwner);
          }
          // Todo Step: 1-4 implement CompleteableFuture
          superOwnersFuture.complete(superOwners);
          runOnUiThread(() -> {
            // Todo Step: 1-3 setup owner spinner
            setupSuperOwnerSpinner(ownerNames);
          });
        },
        failure -> {
          // Todo Step: 1-4 implement CompleteableFuture
          superOwnersFuture.complete(null);
          Log.w(TAG, "Failed to read SuperOwners from Database");
        }
      );
        setupTypeSpinner();
        setupSaveBttn();
    }


    public void setupSuperOwnerSpinner(ArrayList<String> ownerNames){
      superOwnerSpinner.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.simple_spinner_item,
        ownerNames
      ));
    }

    public void setupTypeSpinner(){
      superPetTypeSpinner.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.simple_spinner_item,
        SuperPetTypeEnum.values()
      ));
    }

  public void setupSaveBttn() {
    Button saveBttn = findViewById(R.id.AddASuperPetSaveBttn);
    // TODO step: 1-5 attempt to save a new SuperPet with associated SuperOwner to AWS db
    saveBttn.setOnClickListener(v -> {
      String selectedSuperOwnerString = superOwnerSpinner.getSelectedItem().toString();
      List<SuperOwner> superOwners = null;
      try{
        // Todo Step: 1-4 implement CompleteableFuture
        superOwners = superOwnersFuture.get();
      }
      catch (InterruptedException ie) {
        Log.e(TAG, "InterruptedException while getting Super OWners");
        Thread.currentThread().interrupt();
      } catch (ExecutionException ee) {
        Log.e(TAG, "ExecutionException while getting Super Owners");
      }
      SuperOwner selectedOwner = superOwners.stream().filter(owner -> owner.getName().equals(selectedSuperOwnerString)).findAny().orElseThrow(RuntimeException::new);

      SuperPet newSuperPet = SuperPet.builder()
        .name(((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString())
        .type((SuperPetTypeEnum)superPetTypeSpinner.getSelectedItem())
        .birthDate(new Temporal.DateTime(new Date(), 0))
        .height(Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString()))
        .superOwner(selectedOwner)
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
