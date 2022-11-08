package com.cookieStore.salmonCookies.controllers;

import com.cookieStore.salmonCookies.models.SalmonCookieStore;
import com.cookieStore.salmonCookies.repositories.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SalmonCookieController {
  //TODO: WIRE up the salom Repository with Autowired
  @Autowired
  SalmonCookieStoreRepository salmonCookieStoreRepository;


  // Get method to /
  @GetMapping("/")
  public String getSalmonCookieStores(Model m){
    // db call to get all stores
    List<SalmonCookieStore> stores = salmonCookieStoreRepository.findAll();
    m.addAttribute("stores", stores);
    return "salmon-stores/SalmonCookieStore";
  }

  // Post method to /
  @PostMapping("/")
  public RedirectView createSalmonCookieStore(String name, Integer averageCookiesPerDay){
    // create a new store
    SalmonCookieStore newStore = new SalmonCookieStore(name, averageCookiesPerDay);
    // add it to the DB
    salmonCookieStoreRepository.save(newStore);
    return new RedirectView("/");
  }
}
