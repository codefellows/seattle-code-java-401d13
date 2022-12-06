package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;
import com.zork.zorkmaster.database.ZorkMasterDatabase;
import com.zork.zorkmaster.models.SuperPet;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuperPetActivity extends AppCompatActivity {
  ZorkMasterDatabase zorkMasterDatabase;
  public static final String SUPER_PET_NAME_TAG = "superPetName";

  // TODO: Step 1-1: Add a RecyclerView in the WSYIWYG editor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_pet);
      zorkMasterDatabase = Room.databaseBuilder(
        getApplicationContext(),
        ZorkMasterDatabase.class,
        MainActivity.DATABASE_NAME)
        .fallbackToDestructiveMigration() // If Room gets confused, it tosses your database; don't use this in production!
        .allowMainThreadQueries()
        .build();

        setupRecyclerView();

    }

    public void setupRecyclerView(){
      // TODO Step 2-2:  Make some hardcoded data items
      // TODO Step 6-1 find all SuperPets from database
      List<SuperPet> superPets = zorkMasterDatabase.superPetDao().findAll();
//      superPets.add(new SuperPet("Zork", SuperPet.SuperPetTypeEnum.FIRE, 7, new Date()));
//      superPets.add(new SuperPet("Lemi", SuperPet.SuperPetTypeEnum.FIRE, 7, new Date()));
        // TODO Step 1-2:  Grab the RecyclerView
      RecyclerView superPetRV = findViewById(R.id.SuperPetRecyclerView);
      // TODO Step 1-3: Set the layout manager of the RecyclerView to a LinearLayoutManager
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
      superPetRV.setLayoutManager(layoutManager);
      // TODO Step 1-5: Create and attach the RecyclerView.Adapter
      // TODO Step 2-3: (In this activity and RecyclerViewAdapter) Hand in some data items
      // TODO Step 3-2: (In this activity and RecyclerViewAdapter) Hand in the Activity context
      SuperPetRecyclerViewAdapter adapter = new SuperPetRecyclerViewAdapter(superPets, this);
      superPetRV.setAdapter(adapter);
    }
}
