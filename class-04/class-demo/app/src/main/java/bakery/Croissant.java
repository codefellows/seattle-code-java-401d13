package bakery;

public class Croissant extends Pastry{
  private int flakeFactor;

  public Croissant(String name, boolean isFrench, int flakeFactor) {
    super(name, isFrench);
    this.flakeFactor = flakeFactor;
  }

}
