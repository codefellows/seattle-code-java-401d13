package com.zork.zorkmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.OrderForm;
import com.zork.zorkmaster.activities.SuperPetActivity;
import com.zork.zorkmaster.models.SuperPet;

import java.util.List;

// TODO Step 1-4: Make a class whose sole purpose is to manage RecyclerViews: a RecyclerView.Adapter
  // TODO Step 3-1: (In RecyclerViewAdapter) Clean up the RecyclerView.Adapter references to actually use SuperPetRecyclerViewAdapter
public class SuperPetRecyclerViewAdapter extends RecyclerView.Adapter<SuperPetRecyclerViewAdapter.SuperPetViewHolder> {
  // TODO Step 2-3: (In this activity and RecyclerViewAdapter) Hand in some data items
  List<SuperPet> superPets;
  Context callingActivity;

  public SuperPetRecyclerViewAdapter(List<SuperPet> superPets, Context callingActivity) {
    this.superPets = superPets;
    this.callingActivity = callingActivity;
  }

  @NonNull
  @Override
  public SuperPetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // TODO Step 1-7: (In RecyclerViewAdapter.onCreateViewHolder()) Inflate fragment
    View superPetFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_super_pet, parent, false);
    // TODO Step 1-9: (In RecyclerViewAdapter.onCreateViewHolder()) Attach Fragment to ViewHolder
    return new SuperPetViewHolder(superPetFragment);
  }

  @Override
  public void onBindViewHolder(@NonNull SuperPetViewHolder holder, int position) {
    // TODO Step 2-4: (In RecyclerViewAdapter.onBindViewHolder()) Bind data items to Fragments inside of ViewHolders
    TextView superPetFragmentTextViewName = holder.itemView.findViewById(R.id.SuperPetFragTVName);
    String superPetName = superPets.get(position).getName();
    superPetFragmentTextViewName.setText(position + ". " + superPetName);
    // TODO Step 3-3: (In RecyclerViewAdapter.onBindViewHolder()) Create OnClickListener, make an Intent inside it, and call this Intent with an Extra to go to another Activity
    View superPetItemView = holder.itemView;
    superPetItemView.setOnClickListener(v -> {
      Intent goToOrderFormIntent = new Intent(callingActivity, OrderForm.class);
      goToOrderFormIntent.putExtra(SuperPetActivity.SUPER_PET_NAME_TAG, superPetName);
      callingActivity.startActivity(goToOrderFormIntent);
    });
  }

  @Override
  public int getItemCount() {
    // TODO Step 1-10: (In RecyclerViewAdapter.getItemCount()) For testing purposes, hardcode a large number of items
//    return 100;
  // TODO Step 2-5: (In RecyclerViewAdapter.getItemCount()) Make the size of the list dynamic
    return superPets.size();
  }

  // TODO Step 1-8: (In RecyclerViewAdapter) Make a ViewHolder class to hold a Fragment
    public static class SuperPetViewHolder extends RecyclerView.ViewHolder{
      public SuperPetViewHolder(@NonNull View itemView) {
        super(itemView);
      }
    }
}

