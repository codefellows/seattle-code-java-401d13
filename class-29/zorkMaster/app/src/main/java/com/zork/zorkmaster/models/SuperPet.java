package com.zork.zorkmaster.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

// TODO Step 2-1: Make a data class
//TODO Step 4-3 Change model into an Entity
@Entity
public class SuperPet {
  @PrimaryKey(autoGenerate = true)
  public Long id;
  private String name;
  // TODO Step: 4-2 edit the model class to reflect our enum
  private SuperPetTypeEnum type;
  private java.util.Date birthDate;
  private Integer height;

  public SuperPet(String name, SuperPetTypeEnum type, Integer height, Date birthDate) {
    this.name = name;
    this.type = type;
    this.height = height;
    this.birthDate = birthDate;
  }

  public SuperPet() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SuperPetTypeEnum getType() {
    return type;
  }

  public void setType(SuperPetTypeEnum type) {
    this.type = type;
  }

  // TODO Step: 4-1 create the enum
  public enum SuperPetTypeEnum{
    WATER("Water"),
    ELECTRIC("Electric"),
    ROCK("Rock"),
    FIRE("Fire"),
    GRASS("Grass");

    private final String superPetType;

    SuperPetTypeEnum(String superPetType) {
      this.superPetType = superPetType;
    }

    public String getSuperPetType() {
      return superPetType;
    }

    public static SuperPetTypeEnum fromString(String possibleSuperPetType){
      for (SuperPetTypeEnum type : SuperPetTypeEnum.values()
           ) {
        if(type.superPetType.equals(possibleSuperPetType)) {
          return type;
        }
      }
      return null;
    }


    @NonNull
    @Override
    public String toString() {
      if (superPetType == null){
        return "";
      }
      return superPetType;
    }
  }
}

