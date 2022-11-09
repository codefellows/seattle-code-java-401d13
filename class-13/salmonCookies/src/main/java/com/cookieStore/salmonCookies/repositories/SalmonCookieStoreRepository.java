package com.cookieStore.salmonCookies.repositories;

import com.cookieStore.salmonCookies.models.SalmonCookieStore;
import org.springframework.data.jpa.repository.JpaRepository;

// make a repo for the data value. THIS IS A SERVICE - Singelton design principle == SPRING BEAN!!
// TODO: Turn into a JPA Repo
public interface SalmonCookieStoreRepository extends JpaRepository<SalmonCookieStore, Long> {
  // The reason we are using an interface, is so we can create CUSTOM CRUD queries

  // DARK MAGIC that we made happen with a specific function name
  public SalmonCookieStore findByName(String name);
  public SalmonCookieStore findByAverageCookiesPerDay(Integer averageCookiesPerDay);
}
