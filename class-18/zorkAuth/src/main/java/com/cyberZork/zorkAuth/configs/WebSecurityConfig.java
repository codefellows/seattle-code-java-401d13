package com.cyberZork.zorkAuth.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//TODO: extend WEbSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  //TODO: wire up the SiteUserDetailsServiceImpl
  @Autowired
  SiteUserDetailsServiceImpl siteUserDetailsService;

  //TODO: Password encoder lives
  @Bean
  public PasswordEncoder passwordEncoder(){
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(siteUserDetailsService).passwordEncoder(passwordEncoder());
  }

  //TODO: Configure the security of our application via HttpSecurity
  // This is where we will do the work of how users can access the site
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors().disable()
      .csrf().disable()
                      // ------ Request section
      .authorizeRequests()
      .antMatchers("/", "/login", "/signup").permitAll()
      .anyRequest().authenticated()
      .and()
                      // ----- Login section
      .formLogin()
      .loginPage("/login")
                      // ---- Logout section
      .and()
      .logout()
      .logoutSuccessUrl("/login");
  }
}
