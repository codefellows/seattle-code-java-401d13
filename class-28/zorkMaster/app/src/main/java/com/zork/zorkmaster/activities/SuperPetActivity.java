package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;
import com.zork.zorkmaster.models.SuperPet;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SuperPetActivity extends AppCompatActivity {
  public static final String SUPER_PET_NAME_TAG = "superPetName";

  // TODO: Step 1-1: Add a RecyclerView in the WSYIWYG editor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_pet);
        setupRecyclerView();
    }

    public void setupRecyclerView(){
      // TODO Step 2-2:  Make some hardcoded data items
      List<SuperPet> superPets = new ArrayList<>();
      superPets.add(new SuperPet("Zork","Fire"));
      superPets.add(new SuperPet("Lemi", "Dark"));
      superPets.add(new SuperPet("Harvey", "Psychic"));
      superPets.add(new SuperPet("Pardus", "normal"));
      superPets.add(new SuperPet("Wally", "Chill"));
      superPets.add(new SuperPet("Freya", "Pig"));
      superPets.add(new SuperPet("Itty", "Small"));
      superPets.add(new SuperPet("Bitty", "Small"));
      superPets.add(new SuperPet("Peper", "Fairy"));
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
