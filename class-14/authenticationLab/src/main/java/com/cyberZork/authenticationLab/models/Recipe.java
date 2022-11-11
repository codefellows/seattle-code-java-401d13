package com.cyberZork.authenticationLab.models;

import javax.persistence.*;

// entity, as I understand it, makes the class available to the repository interface
@Entity
public class Recipe {
  // this is how you tell postgres to generate an id for an instance of this object when it's put into the database
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
//  private String[] steps;

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
