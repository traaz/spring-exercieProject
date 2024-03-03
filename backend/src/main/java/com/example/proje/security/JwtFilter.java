package com.example.proje.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//bu sinif sadece validate icin
@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autHeader = request.getHeader("Authorization");
        String token = null;
        String name = null;

        if(autHeader != null && autHeader.startsWith("Bearer ")){
            token = autHeader.substring(7);
            name = jwtService.extractUser(token);
        }
        if(name != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // her request yeni bir thread actigi icin, su an calistigim threadin contextinde bir auth olmamasi lazim
            UserDetails user = userDetailsService.loadUserByUsername(name);
            System.out.println(jwtService.validateToken(token, user));
            if(jwtService.validateToken(token, user)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println(user.getUsername());

            }

    }
        filterChain.doFilter(request, response);

    }
}
