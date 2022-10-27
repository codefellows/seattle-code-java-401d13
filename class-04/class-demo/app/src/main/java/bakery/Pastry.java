package bakery;

import java.util.ArrayList;

public class Pastry {

  private static String name = "Zork";
  private static int count = 0;
  private boolean isFrench;
  private ArrayList<String> ingredients;
  private byte[] color;

  public Pastry(String name, boolean isFrench) {
    this.name = name;
    this.isFrench = isFrench;
    this.ingredients = new ArrayList<>();
  }
  // POLYMORPHISM -> Method overloading
  public Pastry(String _name) {
    this.name = _name;
    this.ingredients = new ArrayList<>();
  }

  // Default constructor
  public Pastry() {
  }

  // Implement Methods eat and aroma

  private void eat(){
    System.out.println("Nom Nom Nom");
  }

  public void aroma(){
    System.out.println("Smells Gooooooood");
    System.out.println(name);
    eat();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isFrench() {
    return isFrench;
  }

  public void setFrench(boolean french) {
    isFrench = french;
  }

  public ArrayList<String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
  }

  public byte[] getColor() {
    return color;
  }

  public void setColor(byte[] color) {
    this.color = color;
  }
}
