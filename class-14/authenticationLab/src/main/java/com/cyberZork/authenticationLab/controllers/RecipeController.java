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
//Display recipe only if user properly logged in (and while the user is in-session), cool.-AA
  //note to NOT name model "user", hibernate does not like this.
  @Autowired
  SiteUserRepository siteUserRepository;

  @GetMapping("/recipe")
  public String getRecipe(HttpServletRequest request, Model m){

    HttpSession session = request.getSession();
    String userName = session.getAttribute("userName").toString();
    // authenticate the user-AW, null meaning nada, no user name is  found in session-AA
    if(userName != null){
      m.addAttribute("userName", userName);
      SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
      m.addAttribute("recipes", siteUser.getRecipes());
      return "recipes.html";
    }
    return "login.html";
  }

}
