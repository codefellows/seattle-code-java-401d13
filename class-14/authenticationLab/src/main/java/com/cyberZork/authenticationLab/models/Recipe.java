package com.cyberZork.authenticationLab.models;

import javax.persistence.*;

// This is the Recipe class, and going to be the model for our recipes.
@Entity //this allows postgress to create the table
public class Recipe {
  @Id // this is the ID that creates a unique identified for the recipe
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
//  private String[] steps;

// This is mapping all the recipes to the site user
  @ManyToOne
  SiteUser siteUser;

  // This is the default constructor for the recipe
  protected Recipe() {
  }

  // This is the constructor for the recipe
  public Recipe(String name, String description) {
    this.name = name;
    this.description = description;
  }

  // This is the getter for the id
  public Long getId() {
    return id;
  }
// This is the getter for the name
  public String getName() {
    return name;
  }
// This is the setter for the name
  public void setName(String name) {
    this.name = name;
  }
// This is the getter for the description
  public String getDescription() {
    return description;
  }
// This is the setter for the description
  public void setDescription(String description) {
    this.description = description;
  }

}
