package com.cyberZork.authenticationLab.models;

import javax.persistence.*;

// makes this class a data table entitt
@Entity
public class Recipe {
  //creates an id for each entry
  @Id
  // auto generates the id value for the data table
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
//  private String[] steps;

  //sets multiple recepies to be connected to one user
  @ManyToOne
  SiteUser siteUser;

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
