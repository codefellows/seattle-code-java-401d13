package com.cookieStore.salmonCookies.repositories;

import com.cookieStore.salmonCookies.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  public Employee findByName(String name);
}
