package zorkSalon.reptile;

import zorkSalon.Animal;

public class Chamaleon extends Reptile {

  private Float currentColor;
  private boolean isPet;
  final String store = "Zorks Salon";

  public Chamaleon(boolean coldBlooded, Float currentColor, boolean hasTail, int legs, boolean isPet) {
    super(coldBlooded, hasTail);
    this.currentColor = currentColor;
    this.isPet = isPet;
//    Animal.incrementAnimals();
  }

  


  public static void changeColor(Integer newColor){
    // change current color
  }


  public Float getCurrentColor() {

    return currentColor;
  }

  public void setCurrentColor(Float currentColor) {
    // check color constraints
    this.currentColor = currentColor;
  }


  public boolean isPet() {
    return isPet;
  }

  public void setPet(boolean pet) {
    isPet = pet;
  }

  @Override
  public String toString() {
    return this.getCurrentColor() + "and exothermic: " + this.isColdBlooded();
  }
}
