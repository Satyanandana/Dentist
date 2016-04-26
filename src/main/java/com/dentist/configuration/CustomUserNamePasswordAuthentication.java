package com.dentist.configuration;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class instance is required to process an authentication .Required to
 * make the custom authentication work in Spring Security.
 **/
public class CustomUserNamePasswordAuthentication extends UsernamePasswordAuthenticationFilter {

}
