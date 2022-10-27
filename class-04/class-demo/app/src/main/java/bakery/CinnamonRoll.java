package bakery;

public class CinnamonRoll extends Pastry{

  private boolean isGlazed;
  private boolean isRolled;

  public CinnamonRoll(String name, boolean isFrench, boolean isGlazed, boolean isRolled) {
    super(name, isFrench);
    this.isGlazed = isGlazed;
    this.isRolled = isRolled;
  }

  public boolean isGlazed() {
    return isGlazed;
  }

  public void setGlazed(boolean glazed) {
    isGlazed = glazed;
  }

  public boolean isRolled() {
    return isRolled;
  }

  public void setRolled(boolean rolled) {
    isRolled = rolled;
  }
}
