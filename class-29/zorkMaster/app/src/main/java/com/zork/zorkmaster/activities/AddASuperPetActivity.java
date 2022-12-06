package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.database.ZorkMasterDatabase;
import com.zork.zorkmaster.models.SuperPet;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

// TODO Step: 5-3 create and activity to take in input and save to DB
public class AddASuperPetActivity extends AppCompatActivity {
  ZorkMasterDatabase zorkMasterDatabase;
  Spinner superPetTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asuper_pet);
        superPetTypeSpinner = findViewById(R.id.AddASuperPetSpinnerType);
        //TODO Step: 5-6 instantiate the DB wherever you need it
      zorkMasterDatabase = Room.databaseBuilder(
        getApplicationContext(),
        ZorkMasterDatabase.class,
        MainActivity.DATABASE_NAME)
        .fallbackToDestructiveMigration() // If Room gets confused, it tosses your database; don't use this in production!
        .allowMainThreadQueries()
        .build();
        setupTypeSpinner();
        setupSaveBttn();
    }

    // TODO Step: 5-4 setup spinner for enum
    public void setupTypeSpinner(){

      superPetTypeSpinner.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.simple_spinner_item,
        SuperPet.SuperPetTypeEnum.values()
      ));
    }

    //TODO Step: 5-5 save superPet to database onClick with the DAO
  public void setupSaveBttn(){
    Button saveBttn = findViewById(R.id.AddASuperPetSaveBttn);
    saveBttn.setOnClickListener(v -> {
      SuperPet newSuperPet = new SuperPet(
        ((EditText)findViewById(R.id.AddASuperPetETName)).getText().toString(),
        SuperPet.SuperPetTypeEnum.fromString(superPetTypeSpinner.getSelectedItem().toString()),
        Integer.parseInt(((EditText)findViewById(R.id.AddASuperPetETHeight)).getText().toString()),
        new Date()
      );
      zorkMasterDatabase.superPetDao().insertASuperPet(newSuperPet);
      Toast.makeText(this, "SuperPet Saved!", Toast.LENGTH_SHORT).show();
    });
  }
}
