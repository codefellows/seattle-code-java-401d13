package com.cookieStore.salmonCookies.models;

import javax.persistence.*;

@Entity
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

  private String name, position;
  private Float totalHours, payPerHour;

  @ManyToOne
  SalmonCookieStore myStore;

  protected Employee() {
  }

  public Employee(String name, String position, Float totalHours, Float payPerHour, SalmonCookieStore myStore) {
    this.name = name;
    this.position = position;
    this.totalHours = totalHours;
    this.payPerHour = payPerHour;
    this.myStore = myStore;
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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Float getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(Float totalHours) {
    this.totalHours = totalHours;
  }

  public Float getPayPerHour() {
    return payPerHour;
  }

  public void setPayPerHour(Float payPerHour) {
    this.payPerHour = payPerHour;
  }
}
