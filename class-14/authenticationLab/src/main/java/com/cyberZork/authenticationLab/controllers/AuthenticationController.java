package com.cyberZork.authenticationLab.controllers;

import com.cyberZork.authenticationLab.models.SiteUser;
import com.cyberZork.authenticationLab.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// controller for authentication directory.
@Controller
public class AuthenticationController {

  @Autowired
  SiteUserRepository siteUserRepository;

  // This is the map to get Home route
  @GetMapping("/")
  public String getHome(){
    return "login.html";
  }


// route to had sign up page.
  @PostMapping("/signup")
  public RedirectView signUp(String userName, String password){
    // hash pw
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
    // new User instance with hashedPW
    SiteUser newUser = new SiteUser(userName, hashedPassword);
    //save to DB
    siteUserRepository.save(newUser);
    // Redirect?
    return new RedirectView("/");
  }


  // route to login page
  @PostMapping("/login")
  public RedirectView login(HttpServletRequest request, String userName, String password){
    // Find user by username
    SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);
    // conditional
      // 1. Is user null? -> login.html
    if (siteUser != null) {
      // 2. compare DB password to given password
      if(BCrypt.checkpw(password, siteUser.getPassword())) {
        // generate a secured session
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName);
        return new RedirectView("/recipe");
      }
      return new RedirectView("/?message=Bad Password");
    }
    return new RedirectView("/?message=No User");
  }

}
