package com.cyberZork.authenticationLab.models;

import javax.persistence.*;

//entity makes allows for a singleton to be created and generates a location on in the db
@Entity
public class Recipe {

  // creates an id that is unique to a new addition to the table
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
//  private String[] steps;

  // connects the many recipes to one user. The common variable name of siteUser is used
  @ManyToOne
  SiteUser siteUser;

  //empty constructor for the singleton creation
  protected Recipe() {
  }

  public Recipe(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
