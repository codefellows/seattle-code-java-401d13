package com.cookieStore.salmonCookies.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: turn into Entity -> DB model
@Entity
public class SalmonCookieStore {

  // TODO: setup ID
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  //IF you need longer than 255 characters, use these 2 annotations
  // @Lob
  // @Type(type = "org.hibernate.type.TextType")

  private String name;
  private Integer averageCookiesPerDay;

  protected SalmonCookieStore() {
  }

  public SalmonCookieStore(String name, Integer averageCookiesPerDay) {
    this.name = name;
    this.averageCookiesPerDay = averageCookiesPerDay;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAverageCookiesPerDay() {
    return averageCookiesPerDay;
  }

  public void setAverageCookiesPerDay(Integer averageCookiesPerDay) {
    this.averageCookiesPerDay = averageCookiesPerDay;
  }
}
