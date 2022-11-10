package com.cyberZork.authenticationLab.models;

import javax.persistence.*;
import java.util.List;

// This is the SiteUser class, and going to be the model for our users.
@Entity
public class SiteUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
// This is needed so the site user can map to all the different recipes
  @OneToMany(mappedBy = "siteUser")
  // This is where all the recipes are going to live
  List<Recipe> recipes;

  private String userName;
  private String password;

  // This is the constructor for the site user
  public SiteUser(String userName, String password){
    this.userName = userName;
    this.password = password;
  }

  // This is the default constructor for the site user
  protected SiteUser(){
  }

  // This is the getter for the id
  public Long getId() {
    return id;
  }
// This is the getter for the user name
  public String getUserName() {
    return userName;
  }

  // This is the setter for the user name
  public void setUserName(String userName) {
    this.userName = userName;
  }
// This is the getter for the password
  public String getPassword() {
    return password;
  }
// This is the setter for the password
  public void setPassword(String password) {
    this.password = password;
  }
// This is the getter for the recipes
  public List<Recipe> getRecipes() {
    return recipes;
  }
}
