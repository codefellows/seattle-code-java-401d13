package zorkSalon.reptile;

import zorkSalon.Animal;
import zorkSalon.Lifestyle;

public class Chamaleon extends Reptile {

  private Float currentColor;
  private boolean isPet;
  final String store = "Zorks Salon";

  public Chamaleon(Integer legs, boolean isColdBlooded, boolean hasTail, Float currentColor, boolean isPet) {
    super(legs, isColdBlooded, hasTail);
    this.currentColor = currentColor;
    this.isPet = isPet;
  }


  public static void changeColor(Integer newColor){
    // change current color
  }

  @Override
  public void reproduce(int days) {
    super.reproduce(days);
    System.out.println("Born from an egg");
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
