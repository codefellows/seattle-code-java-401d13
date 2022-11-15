package com.cyberZork.zorkAuth.configs;

import com.cyberZork.zorkAuth.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//TODO: use the @Service here
@Service // Spring autodetects and loads
public class SiteUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  SiteUserRepository siteUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO: return a SiteUser -> optional csat to UserDetails
    return (UserDetails) siteUserRepository.findByUsername(username);
  }
}
