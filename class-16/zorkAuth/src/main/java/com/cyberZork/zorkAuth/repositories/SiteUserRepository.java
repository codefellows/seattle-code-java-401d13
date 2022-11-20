package com.cyberZork.zorkAuth.repositories;

import com.cyberZork.zorkAuth.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  public SiteUser findByUsername(String username);
}
