package com.udacity.jwdnd.course1.cloudstorage.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfigAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(getPsswordEncoder());
    }
       
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
        http
        .headers().frameOptions().disable().and()
        .csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/signup","/static/**","/login").permitAll()
        	.antMatchers("/api/*").authenticated()
        	.antMatchers("/home").authenticated()
        .and()
		    .formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home",true)
			.failureUrl("/login?error")
	    .and() 
		.logout()
	        .invalidateHttpSession(true)
	        .clearAuthentication(true)
	        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        .logoutSuccessUrl("/login?logout")
	     .and()
			.exceptionHandling().accessDeniedPage("/login?error");
	
    }
     
    @Bean
	public PasswordEncoder getPsswordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
  
}
