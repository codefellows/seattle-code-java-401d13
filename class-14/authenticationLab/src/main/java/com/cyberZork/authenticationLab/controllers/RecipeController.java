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
// This is the authentication controller
public class RecipeController {

  @Autowired
  SiteUserRepository siteUserRepository;
  // This is the mapping for recipes with a login authentication form
  @GetMapping("/recipe")
  // This is the model for the recipe
  public String getRecipe(HttpServletRequest request, Model m){

    // Get the session
    HttpSession session = request.getSession();
    // Get the user name from the session
    String userName = session.getAttribute("userName").toString();
    // authenticate the user
    // if user is not authenticated, redirect to login
    // if user is authenticated, show the recipe
    if(userName != null){
      // Get the user from the database
      m.addAttribute("userName", userName);
      SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
      // Add the user to the model
      m.addAttribute("recipes", siteUser.getRecipes());
      // Return the recipe page
      return "recipes.html";
    }
    // Redirect to the login page
    return "login.html";
  }

}
