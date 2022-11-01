package zorkSalon;

public interface Lifestyle {

  default void reproduce(int days){
    System.out.println("Born in: " + days + "days");
  }

  default void grow(){}

  default void die(){}

}
