package bakery;

import java.util.ArrayList;

public class Bakery {

  private boolean smellsGood;
  private String name;
  private ArrayList<Pastry> pastries;

  // Constructor METHOD!
  public Bakery(boolean smellsGood, String name){
    this.smellsGood = smellsGood;
    this.name = name;
    this.pastries = new ArrayList<>();
  }

  public boolean isSmellsGood() {
    return smellsGood;
  }

  public void setSmellsGood(boolean smellsGood) {
    // custom logic - SHOULD we change this?
    this.smellsGood = smellsGood;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Pastry> getPastries() {
    return pastries;
  }

  public void setPastries(ArrayList<Pastry> pastries) {
    // custom logic
    this.pastries = pastries;
  }
}
