package com.cyberZork.authenticationLab.models;

import javax.persistence.*;
import java.util.List;

// sets the class as an instance of a data table entity
@Entity
public class SiteUser {

//  Creates a unique id for each item
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // establishes one SiteUser to be connected to many recipes
  @OneToMany(mappedBy = "siteUser")
  List<Recipe> recipes;

  private String userName;
  private String password;

  public SiteUser(String userName, String password){
    this.userName = userName;
    this.password = password;
  }

  protected SiteUser(){
  }

  public Long getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }
}
