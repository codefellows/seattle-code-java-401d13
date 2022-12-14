package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.SuperPet;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuperPetActivity extends AppCompatActivity {
  public final static String TAG = "SuperPetActivity";
  public static final String SUPER_PET_NAME_TAG = "superPetName";
  public static final String SUPER_PET_ID_TAG = "superPetId";
  SuperPetRecyclerViewAdapter adapter;
  private List<SuperPet> superPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_pet);
        setupRecyclerView();
    }

  @Override
  protected void onResume() {
    super.onResume();
    Amplify.API.query(
      ModelQuery.list(SuperPet.class),
      success -> {
        Log.i(TAG, "Read Super Pets successfully");
        for (SuperPet databaseSuperPet : success.getData()) {
          superPets.add(databaseSuperPet);
        }
        runOnUiThread(() -> adapter.notifyDataSetChanged()); // since this runs asynchronously, the adapter may already have rendered, so we have to tell it to update

      },
      failure -> Log.e(TAG, "Failed to read Super Pets from database")
    );
  }

  public void setupRecyclerView(){
      superPets = new ArrayList<>();
      RecyclerView superPetRV = findViewById(R.id.SuperPetRecyclerView);
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
      superPetRV.setLayoutManager(layoutManager);
      adapter = new SuperPetRecyclerViewAdapter(superPets, this);
      superPetRV.setAdapter(adapter);
    }
}
