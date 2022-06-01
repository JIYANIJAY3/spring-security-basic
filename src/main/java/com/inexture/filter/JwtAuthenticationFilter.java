package com.inexture.filter;//package com.inexture.filter;

import com.inexture.service.CustomUserDetailsService;
import com.inexture.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get jwt
        //Bearer
        //validate

       String requestTokenHeader =  request.getHeader(HttpHeaders.AUTHORIZATION);

       System.out.println("requestTokenHeader: "+requestTokenHeader);
       String username = null;
       String jwtToken = null;

       // check null and format
       if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
       {
           jwtToken = requestTokenHeader.substring(7);

           try{
               username = this.jwtUtil.extractUsername(jwtToken);
           }
           catch (Exception e)
           {
              e.printStackTrace();
           }

           assert username != null;
           UserDetails userDetails =  this.customUserDetailsService.loadUserByUsername(username);

           System.out.println("SecurityContextHolder.getContext().getAuthentication(): "+SecurityContextHolder.getContext().getAuthentication());
           //security
           if(SecurityContextHolder.getContext().getAuthentication() == null)
           {
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               System.out.println("token valid");
           }
           else
           {
               System.out.println("token is not validated");
           }
       }
       else
       {
           System.out.println("check header");
       }
       filterChain.doFilter(request,response);
    }
}
