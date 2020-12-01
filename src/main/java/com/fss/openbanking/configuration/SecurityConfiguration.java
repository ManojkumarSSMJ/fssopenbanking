/**
 * 
 */
package com.fss.openbanking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

/**
 * @author Selvakumar
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	

	 
	 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
       
		  http
          .authorizeRequests()
              .antMatchers("/","/**","/index","/logout","/tppAccounts","/tppAuthenticate", "/tppLogin","/tppDashboard",
            		  "/authorize","/showBanks","/updateInstitutions","/css/**", "/fonts/**","/images/**","/js/**").permitAll() 
              .anyRequest().authenticated()
              .and()
              .formLogin()
              .defaultSuccessUrl("/dashboard")
              .loginPage("/login")
              .loginProcessingUrl("/j_spring_security_check")
              .failureUrl("/loginfail")
              .permitAll()
              .and()
              .logout()
              .logoutUrl("/logout")
              .logoutSuccessUrl("/index")
              .permitAll();
   }
	
  
      
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   
   @Override
   public void configure(WebSecurity web) throws Exception {
       //@formatter:off
       super.configure(web);
       web.httpFirewall(defaultHttpFirewall());
 
   }
   @Bean
   public HttpFirewall defaultHttpFirewall() {
       return new DefaultHttpFirewall();
   }
   
  
}
