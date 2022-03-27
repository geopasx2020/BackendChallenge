package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 private final UserDetailsService userDetailsService;
	 public WebSecurityConfig(UserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	        
	    }
	 
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.authorizeRequests()
    	.antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
    	.antMatchers("/").permitAll()
    	.antMatchers("/admin/**").hasRole("ADMIN")
    	.anyRequest().authenticated()
    	.and()
    	.formLogin()
    	.loginPage("/login")
    	.defaultSuccessUrl("/home")
    	.failureUrl("/login?error")
    	.permitAll()
    	.and()
    	.logout()
    	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    	.logoutSuccessUrl("/login?logout")
    	.deleteCookies("remember-me")
    	.permitAll()
    	.and()
    	.rememberMe()
    	.key("my-secure-key")
    	.rememberMeCookieName("my-remember-me-cookie")
    	.tokenValiditySeconds(24 * 60 * 60)
    	.and()
    	.exceptionHandling()
    	;
    	}
}
