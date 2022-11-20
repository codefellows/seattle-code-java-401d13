package com.cyberZork.authenticationLab.repositories;

import com.cyberZork.authenticationLab.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// This is the site user repository so we can use the Jpa site user methods
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  public SiteUser getSiteUserByUserName(String UserName);

}
