package bakery;

public class Danish extends Pastry{

  private boolean isFilled;
  private String fruitType;

  public Danish(String name, boolean isFrench, boolean isFilled, String fruitType) {
    super(name, isFrench);
    this.isFilled = isFilled;
    this.fruitType = fruitType;
  }

  @Override
  public void aroma() {
    super.aroma();
    System.out.println("MMMMMMMMMMMM");
  }

  public boolean isFilled() {
    return isFilled;
  }

  public void setFilled(boolean filled) {
    isFilled = filled;
  }

  public String getFruitType() {
    return fruitType;
  }

  public void setFruitType(String fruitType) {
    this.fruitType = fruitType;
  }
}
