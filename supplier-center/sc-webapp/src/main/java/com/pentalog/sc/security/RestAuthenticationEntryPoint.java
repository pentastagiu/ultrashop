package com.pentalog.sc.security;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.springframework.security.core.AuthenticationException;  
import org.springframework.security.web.AuthenticationEntryPoint;  
import org.springframework.stereotype.Component;  
  
/**
 * Class that will serve as the entry point for REST authentication
 *
 */
@Component("restAuthenticationEntryPoint")  
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {  
   
	/**
	 * This method will reject every unauthenticated request and send error code 401
	 */
   @Override  
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException ) throws IOException {  
      response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );  
   }  
}  