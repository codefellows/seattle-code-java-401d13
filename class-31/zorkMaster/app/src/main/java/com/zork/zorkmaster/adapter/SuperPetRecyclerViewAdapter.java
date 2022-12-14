package com.zork.zorkmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.SuperPet;
import com.zork.zorkmaster.R;
import com.zork.zorkmaster.activities.OrderForm;
import com.zork.zorkmaster.activities.SuperPetActivity;
import com.zork.zorkmaster.activities.SuperPetDetailActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SuperPetRecyclerViewAdapter extends RecyclerView.Adapter<SuperPetRecyclerViewAdapter.SuperPetViewHolder> {
  List<SuperPet> superPets;
  Context callingActivity;

  public SuperPetRecyclerViewAdapter(List<SuperPet> superPets, Context callingActivity) {
    this.superPets = superPets;
    this.callingActivity = callingActivity;
  }

  @NonNull
  @Override
  public SuperPetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View superPetFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_super_pet, parent, false);

    return new SuperPetViewHolder(superPetFragment);
  }

  @Override
  public void onBindViewHolder(@NonNull SuperPetViewHolder holder, int position) {
    TextView superPetFragmentTextViewName = holder.itemView.findViewById(R.id.SuperPetFragTVName);
    ImageView superPetFragImageView;
    SuperPet superPet = superPets.get(position);

    superPetFragmentTextViewName.setText((position+1) + ". " + superPet.getName()
      + "\n" + superPet.getHeight()
      + "\n" + superPet.getType());
    View superPetItemView = holder.itemView;
    superPetItemView.setOnClickListener(v -> {
      Intent goToOrderFormIntent = new Intent(callingActivity, SuperPetDetailActivity.class);
      goToOrderFormIntent.putExtra(SuperPetActivity.SUPER_PET_ID_TAG, superPet.getId());
      callingActivity.startActivity(goToOrderFormIntent);
    });
  }


  @Override
  public int getItemCount() {
    return superPets.size();
  }

    public static class SuperPetViewHolder extends RecyclerView.ViewHolder{
      public SuperPetViewHolder(@NonNull View itemView) {
        super(itemView);
      }
    }
}

