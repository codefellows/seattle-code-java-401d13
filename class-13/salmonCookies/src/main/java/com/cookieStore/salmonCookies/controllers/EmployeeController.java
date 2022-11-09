package com.cookieStore.salmonCookies.controllers;

import com.cookieStore.salmonCookies.models.Employee;
import com.cookieStore.salmonCookies.models.SalmonCookieStore;
import com.cookieStore.salmonCookies.repositories.EmployeeRepository;
import com.cookieStore.salmonCookies.repositories.SalmonCookieStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class EmployeeController {

  @Autowired
  EmployeeRepository employeeRepository;
  @Autowired
  SalmonCookieStoreRepository salmonCookieStoreRepository;

  // Get to "/employees"
  @GetMapping("/employees")
  public String getAllEmployees(Model m){
    List<Employee> employees = employeeRepository.findAll();
    m.addAttribute("employees", employees);
    return "salmon-stores/SalmonCookieStore";
  }

  // Post to /employees
  @PostMapping("/employees")
  public RedirectView createNewEmployee(String name, String position, Float totalHours, Float payPerHour, String store){
    SalmonCookieStore salmonCookieStore = salmonCookieStoreRepository.findByName(store);
    Employee newEmployee = new Employee(name, position, totalHours, payPerHour, salmonCookieStore);
    employeeRepository.save(newEmployee);
    return new RedirectView("/");
  }
}
