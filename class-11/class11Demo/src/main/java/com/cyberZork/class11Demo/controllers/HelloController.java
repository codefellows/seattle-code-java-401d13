package com.cyberZork.class11Demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
  // route in spring == Path + callback()
  // a home route
  // ye old Spring route creation
  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseBody // IF you want a BODY back
  public String getHome(){
    return "Hello World";
  }

  // NEW AND AWESOME WAY
  @GetMapping("/hi")
  public String getHi(){
      // IF NO ResponseBody, will render the template matching the returned string
      return "hello";
  }

  // route with URL params
  @GetMapping("/sayhello/{name}")
  @ResponseBody //
  public String sayHello(@PathVariable String name){
    return "Hello " + name;
  }

  //  route with URL params using a Spring model(not Model from MVC)
  @GetMapping("/model/{name}")
  public String modelMe(@PathVariable String name, Model m){
    m.addAttribute("name", name);
    m.addAttribute("age", 70);
    return "friends/newFriend.html";
  }

}
