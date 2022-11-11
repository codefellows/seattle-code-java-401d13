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

  @Autowired
  SiteUserRepository siteUserRepository;

  @GetMapping("/")
  public String getHome(){
    return "login.html";
  }
  // display login page -AA

  @PostMapping("/signup")
  //Sign up and store hashed and salted password for a new user, redirect - why? Login again? -AA
  public RedirectView signUp(String userName, String password){
    // hash pw
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));//Bcrypt did not like "2" or over 30.-AA
    // new User instance with hashedPW
    SiteUser newUser = new SiteUser(userName, hashedPassword);//da hash is in!
    //save to DB
    siteUserRepository.save(newUser);
    // Redirect?
    return new RedirectView("/");
  }

  @PostMapping("/login")
  //Finding a user attempting to login and comapre the hashes (attempted password and what is in db)-AA
  // Creating session for further access, based on confirmed "in-session" status to "protected" pages -AA
  public RedirectView login(HttpServletRequest request, String userName, String password){
    // Find user by username
    SiteUser siteUser = siteUserRepository.getSiteUserByUserName(userName);//using that custom repo name -AA
    // conditional
      // 1. Is user null? -> login.html
    if (siteUser != null ) {
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

//where we _initially_ "writing" the session -AA

