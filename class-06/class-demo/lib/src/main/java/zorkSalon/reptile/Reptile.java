package zorkSalon.reptile;

import zorkSalon.Animal;

public class Reptile extends Animal {
  private boolean isColdBlooded;
  private boolean hasTail;

  public Reptile(boolean isColdBlooded, boolean hasTail) {
    super(legs);
    this.isColdBlooded = isColdBlooded;
    this.hasTail = hasTail;
  }

  public boolean isColdBlooded() {
    return isColdBlooded;
  }

  public void setColdBlooded(boolean coldBlooded) {
    isColdBlooded = coldBlooded;
  }

  public boolean isHasTail() {
    return hasTail;
  }

  public void setHasTail(boolean hasTail) {
    this.hasTail = hasTail;
  }

  // Reptile behaviors

  void move(){}

  void bask(){}

  void shedding(){}

  @Override
  public String toString() {
    return "A reptile has: " + this.getLegs() + "legs";
  }
}
