package com.techbasket.techbasket.configs;

import com.techbasket.techbasket.service.UserService;
import com.techbasket.techbasket.util.CookieExtractor;
import com.techbasket.techbasket.util.JwtUtil;
import com.techbasket.techbasket.util.cookieExtractor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JWTfilter extends OncePerRequestFilter {

  @Autowired
  UserService userService;

  private final JwtUtil jwtUtil;
  private final CookieExtractor cookieExtractor;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    // cookie holder
    String desiredCookie;
    String username;
    String password;

    try {
      // mining cookie
      desiredCookie = cookieExtractor.extractCookie(request.getHeader("Cookie"), "user-auth-key");

      // set credentials
      String credentials[] = jwtUtil
          .extract(desiredCookie)
          .getSubject()
          .split(";");
      username = credentials[0];
      password = credentials[1];
    } catch (Exception err) {
      // onerror
      desiredCookie = null;
      username = null;
      password = null;
    }

    if (desiredCookie != null && userService.isHeThere(username, password)) {
      // do more jwt based auth before setting Auth
      SecurityContextHolder
          .getContext()
          .setAuthentication(new Authenticator(null));
    }

    filterChain.doFilter(request, response);
  }
}
