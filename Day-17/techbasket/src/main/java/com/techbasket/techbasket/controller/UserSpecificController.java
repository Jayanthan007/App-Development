package com.techbasket.techbasket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techbasket.techbasket.entity.ProductEntity;
import com.techbasket.techbasket.service.UserService;
import com.techbasket.techbasket.util.CookieExtractor;
import com.techbasket.techbasket.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/userspecific")
@RequiredArgsConstructor
public class UserSpecificController {

  @Autowired
  UserService userService;

  private final CookieExtractor cookieExtractor;
  private final JwtUtil jwtUtil;

  @GetMapping("/cart")
  public String cartPrds(@RequestHeader(value = "Cookie") String cookie) {
    String desiredCookie = cookieExtractor.extractCookie(cookie, "user-auth-key");

    String username;
    String password;

    try {
      String credentials[] = jwtUtil
          .extract(desiredCookie)
          .getSubject()
          .split(";");
      username = credentials[0];
      password = credentials[1];
    } catch (Exception err) {
      username = null;
      password = null;
    }

    return "Cookie you passed has " + username + " " + password;
  }

  @GetMapping("/amIaSeller")
  public ResponseEntity<Boolean> amIaSeller(@RequestHeader(value = "Cookie") String cookie) {
    String desiredCookie = cookieExtractor.extractCookie(cookie, "user-auth-key");

    String username;
    String password;

    try {
      String credentials[] = jwtUtil
          .extract(desiredCookie)
          .getSubject()
          .split(";");
      username = credentials[0];
      password = credentials[1];
    } catch (Exception err) {
      username = null;
      password = null;
    }
    return ResponseEntity.ok().body(userService.doesSellerExist(username, password));
  }

  @PostMapping("/addProduct")
  public boolean addProduct(@RequestHeader(value = "Cookie") String cookie, @RequestBody ProductEntity product) {
    String desiredCookie = cookieExtractor.extractCookie(cookie, "user-auth-key");

    String username;
    String password;

    try {
      String credentials[] = jwtUtil
          .extract(desiredCookie)
          .getSubject()
          .split(";");
      username = credentials[0];
      password = credentials[1];
    } catch (Exception err) {
      username = null;
      password = null;
    }
    return userService.addProduct(username, password, product);
  }
}
