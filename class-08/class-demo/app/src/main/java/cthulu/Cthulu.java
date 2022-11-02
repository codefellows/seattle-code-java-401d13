package cthulu;

import java.util.ArrayList;

public class Cthulu {
  public boolean isSleeping;
  public int tentacles;
  public boolean isPresidentialCandidate;
  public boolean isSwimming;
  public float numberOfDimensions;
  ArrayList<Worshiper> worshipers;
  public String incantation;


  public Cthulu(boolean isSleeping, int tentacles, boolean isPresidentialCandidate, boolean isSwimming, float numberOfDimensions, ArrayList<Worshiper> worshipers, String incantation) {
    this.isSleeping = isSleeping;
    this.tentacles = tentacles;
    this.isPresidentialCandidate = isPresidentialCandidate;
    this.isSwimming = isSwimming;
    this.numberOfDimensions = numberOfDimensions;
    this.worshipers = worshipers;
    this.incantation = incantation;
  }

  public void awaken(){
    System.out.println("YEET");
  }
}
