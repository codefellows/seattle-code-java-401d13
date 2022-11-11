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

@Controller
public class AuthenticationController {

  //wires up the database to the application
  @Autowired
  SiteUserRepository siteUserRepository;

  //get map that brings the user back to the login page
  @GetMapping("/")
  public String getHome(){
    return "login.html";
  }

  //hashes the password and saves the protected password to the database for future reference.
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

  //checks to make sure that the user has an account and that the password is correct. In addition, this will send the user to their recipes is all of their information is correct on sign-in
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
