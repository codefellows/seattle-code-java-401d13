package com.cyberZork.authenticationLab.controllers;

import com.cyberZork.authenticationLab.models.SiteUser;
import com.cyberZork.authenticationLab.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RecipeController {

  //wires up the application to the database on pgadmin
  @Autowired
  SiteUserRepository siteUserRepository;

  //when the user navigates to the recipe page, the page authorizes the user to ensure that they still have appropriate access. Recipes are currently not loaded to this page.
  @GetMapping("/recipe")
  public String getRecipe(HttpServletRequest request, Model m){

    HttpSession session = request.getSession();
    String userName = session.getAttribute("userName").toString();
    // authenticate the user
    if(userName != null){
      m.addAttribute("userName", userName);
      SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
      m.addAttribute("recipes", siteUser.getRecipes());
      return "recipes.html";
    }
    return "login.html";
  }

}
