package com.quickclinic.patient.filter;

import com.quickclinic.patient.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
           String authorizationHeader = request.getHeader("Authorization");
           String username = null;
           String jwt = null;
           if (authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
               jwt = authorizationHeader.substring(7);
               username= jwtUtil.extractUsername(jwt);
           }

           if (username != null && SecurityContextHolder.getContext().getAuthentication()==null){
              if (Boolean.TRUE.equals(jwtUtil.validateToken(jwt))){
                  List<String> roles = jwtUtil.extractRoles(jwt);

                  List<SimpleGrantedAuthority> authorities = roles.stream()
                          .map(SimpleGrantedAuthority::new)
                          .toList();

                  UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                          username,
                          null,
                          authorities
                  );
                  auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  SecurityContextHolder.getContext().setAuthentication(auth);
              }


           }
           filterChain.doFilter(request,response);
    }
}
