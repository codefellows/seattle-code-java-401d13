package pokemon;

import pokemon.ability.AbilityWrapper;
import pokemon.type.TypeWrapper;

import java.util.ArrayList;

public class Pokemon {
  private String name;
  private Integer height;
  TypeWrapper[] types;
  ArrayList<AbilityWrapper> abilities;

  public Pokemon(String name, Integer height, TypeWrapper[] types, ArrayList<AbilityWrapper> abilities) {
    this.name = name;
    this.height = height;
    this.types = types;
    this.abilities = abilities;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public TypeWrapper[] getTypes() {
    return types;
  }

  public void setTypes(TypeWrapper[] types) {
    this.types = types;
  }

  public ArrayList<AbilityWrapper> getAbilities() {
    return abilities;
  }

  public void setAbilities(ArrayList<AbilityWrapper> abilities) {
    this.abilities = abilities;
  }
}
