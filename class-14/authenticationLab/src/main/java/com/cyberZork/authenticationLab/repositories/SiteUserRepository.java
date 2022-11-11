package com.cyberZork.authenticationLab.repositories;

import com.cyberZork.authenticationLab.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//extends the JpaRepository to give our Site UserModel access to all the methods a repository has
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  public SiteUser getSiteUserByUserName(String UserName);

}
