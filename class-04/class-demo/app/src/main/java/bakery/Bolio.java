package bakery;

import java.util.ArrayList;

public class Bolio extends Pastry{

  private boolean isFilled;
  private ArrayList<String> toppings;

  public Bolio(String name, boolean isFrench, boolean isFilled) {
    super(name, isFrench);
    this.isFilled = isFilled;
    this.toppings = new ArrayList<>();
  }

}
