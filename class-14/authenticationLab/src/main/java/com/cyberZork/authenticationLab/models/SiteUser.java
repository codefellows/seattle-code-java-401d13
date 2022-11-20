package com.cyberZork.authenticationLab.models;

import javax.persistence.*;
import java.util.List;

//creating the ability for a singleton to be initialized and used in db
@Entity
public class SiteUser {

  //allows for the generation of a unique id for each addition to the db
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //one user to many recipes. The mapping technique to by using the siteUser common variable
  @OneToMany(mappedBy = "siteUser")
  List<Recipe> recipes;

  private String userName;
  private String password;

  public SiteUser(String userName, String password){
    this.userName = userName;
    this.password = password;
  }

  //empty constructor for singleton initialization
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
