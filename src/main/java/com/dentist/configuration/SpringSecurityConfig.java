package com.dentist.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.dentist.webapp")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**", "/signup/*", "/about", "/login/*").permitAll().anyRequest()
				.authenticated()
				.and().formLogin()
				                 .loginPage("/login/form")
				                 .permitAll()
			    .and().logout()
			                  .invalidateHttpSession(true)
				              .clearAuthentication(true)
				             // .deleteCookies("JSESSIONID","USER")
				              .addLogoutHandler(new ProperCookieClearingLogoutHandler("JSESSIONID","USER"))
				              .permitAll()
			    .and().sessionManagement()
				              .maximumSessions(1)
				              .maxSessionsPreventsLogin(true)
				              .expiredUrl("/accessDenied")
				              .sessionRegistry(sessionRegistry);

		}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.userDetailsService(userDetailsService);
	 * 
	 * }
	 */
}