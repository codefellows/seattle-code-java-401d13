package com.cyberZork.zorkAuth.controllers;

import com.cyberZork.zorkAuth.models.SiteUser;
import com.cyberZork.zorkAuth.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

@Controller
public class SiteUserController {

  @Autowired
  SiteUserRepository siteUserRepo;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  HttpServletRequest request;


  @GetMapping("/")
  public String getHomePage(Model m, Principal p){
    if(p != null){
      String username = p.getName();
      SiteUser foundUser = siteUserRepo.findByUsername(username);

      m.addAttribute("username", username);
      m.addAttribute("fName", foundUser.getFirstName());
    }

    throw new RuntimeException("It's a 404");
//    return "index";
  }

  @GetMapping("login")
  public String getLoginPage(){
    return "login";
  }

  @GetMapping("/signup")
  public String getSignupPage(){
    return "signup";
  }

  @PostMapping("/signup")
  public RedirectView createUser(String username, String password, String firstName, Date dateOfBirth){
    // hash the PW
    String hashedPW = passwordEncoder.encode(password);
    // create new user
    SiteUser newUser = new SiteUser(username, hashedPW, firstName);
    // save the user
    siteUserRepo.save(newUser);
    // auto login -> httpServletRequest
    authWithHttpServletRequest(username, password);
    return new RedirectView("/");
  }

  public void authWithHttpServletRequest(String username, String password){
    try {
      request.login(username, password);
    } catch (ServletException e) {
      e.printStackTrace();
    }
  }

  @GetMapping("/sauce")
  public String getSauce(){
    return "secret";
  }

  @GetMapping("/test")
  public String getTestPage(Principal p, Model m){
    String username = p.getName();
    SiteUser siteUser = siteUserRepo.findByUsername(username);

    m.addAttribute("firstName", siteUser.getFirstName());

    return "test";
  }

  @GetMapping("/users/{id}")
  public String getUserInfo(Model m, Principal p, @PathVariable Long id){
    SiteUser authenticatedUser = siteUserRepo.findByUsername(p.getName());
    m.addAttribute("authenticatedUsername", authenticatedUser.getUsername());

    SiteUser viewUser = siteUserRepo.findById(id).orElseThrow();
    m.addAttribute("viewUsername", viewUser.getUsername());
    m.addAttribute("viewUserID", viewUser.getId());

    return "user-info";
  }

  @PutMapping("/users/{id}")
  public RedirectView editUserInfo(Model m, Principal p, @PathVariable Long id, String username, RedirectAttributes redir) throws ServletException {
    SiteUser userToBeEdited = siteUserRepo.findById(id).orElseThrow();
    if(p.getName().equals(userToBeEdited.getUsername())){
      userToBeEdited.setUsername(username);
      siteUserRepo.save(userToBeEdited);
      request.logout();
    } else {
      redir.addFlashAttribute("errorMessage", "Cannot edit another user's info");
    }
    return new RedirectView("/users/" + id);
  }
}
