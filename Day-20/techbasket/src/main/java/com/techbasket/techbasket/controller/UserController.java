package com.techbasket.techbasket.controller;

import com.techbasket.techbasket.entity.CountryEntity;
import com.techbasket.techbasket.entity.ProductEntity;
import com.techbasket.techbasket.entity.UserCredentials;
import com.techbasket.techbasket.entity.UserEntity;
import com.techbasket.techbasket.service.ProductService;
import com.techbasket.techbasket.service.UserService;
import com.techbasket.techbasket.util.CookieExtractor;
import com.techbasket.techbasket.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  @Autowired
  UserService userService;
  
  private final ProductService productService;
  private final JwtUtil jwtUtil;
  private final CookieExtractor cookieExtractor;

  @PostMapping("/login")
  public ResponseEntity<Boolean> a(
      @RequestBody UserCredentials userCredentials) {
    // check if not valid user
    if (!userService.isHeThere(
        userCredentials.getUsername(),
        userCredentials.getPassword()))
      return ResponseEntity.ok().body(false);

    String token = jwtUtil.generateToken(
        userCredentials.getUsername(),
        userCredentials.getPassword());

    return ResponseEntity
        .ok()
        .header("set-cookie",
            ("user-auth-key=" + token + ";HttpOnly"))
        .body(true);
  }

  @PostMapping("/addMe")
  public boolean addMe(@RequestBody UserEntity user) {
    return userService.addMe(user);
  }

  @PostMapping("/addMeAsSeller")
  public boolean addMeAsSeller(@RequestBody CountryEntity cEntity, @RequestHeader(value = "Cookie") String cookie) {
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

    return userService.addSeller(username, password, cEntity.getCountry());
  }

  @GetMapping("/z")
  public String b() {
    return "hi z";
  }

  @GetMapping("/getProducts")
  public Iterable<ProductEntity> getProduct() {
  return productService.getAll();
  }
}
