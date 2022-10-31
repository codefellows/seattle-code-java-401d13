package zorkSalon;

abstract public class Animal {
  private Integer legs;
  static Integer Animals = 0;
  final static String store = "Zorks Salon";

  public Animal(Integer legs) {
    this.legs = legs;
  }

  public Integer getLegs() {
    return legs;
  }

  public void setLegs(Integer legs) {
    this.legs = legs;
  }

  protected void eat(){
    System.out.println("NOM NOM NOM");
  }
  void poop(){
    incrementAnimals();
  }


  static void makeNoise(){
    System.out.println("LOUD NOISES");
  }

  static void incrementAnimals(){
    Animals = Animals + 1;
  }


}
