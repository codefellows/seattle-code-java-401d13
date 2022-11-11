package com.cyberZork.authenticationLab.repositories;

import com.cyberZork.authenticationLab.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This is where some postgres black magic is occuring
// jpa repository does a bunch of work to map objects to tables behind the scenes
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  // jpa repository has many built in methods
  // but if you want any that are specific to your object you write them here
  public SiteUser getSiteUserByUserName(String UserName);

}
