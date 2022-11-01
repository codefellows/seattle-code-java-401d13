package zorkSalon.mammals;

import zorkSalon.Animal;
import zorkSalon.Flying;
import zorkSalon.Lifestyle;

public class Mammal extends Animal implements Lifestyle, Flying {

  private boolean hasFur;
  private boolean hasTusks;

  public Mammal(Integer legs) {
    super(legs);
  }

  // Behaviors


  @Override
  public void die() {
    System.out.println("Never Die");
  }

  // TODO: INTERFACE
  void birth(){}

  void produceMilk(){}
}
