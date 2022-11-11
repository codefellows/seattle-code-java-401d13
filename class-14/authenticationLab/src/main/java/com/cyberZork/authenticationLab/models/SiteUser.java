package com.cyberZork.authenticationLab.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class SiteUser {
  //note to NOT name model "user", hibernate does not like this.-AA
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "siteUser")
  List<Recipe> recipes; // this line disappeared in class causing a funky error (wonder what error) -AA

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
  } //is this used in sign up?-AA

  public List<Recipe> getRecipes() {
    return recipes;
  }
}
