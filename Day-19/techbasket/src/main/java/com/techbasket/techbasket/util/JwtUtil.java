package com.techbasket.techbasket.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

  private String secret =
    "VCCjuIfD65MtvNthatDQCmfFtpqdgRb9GIXhoQlPBB7qzeAXZcDJw1MeZ480fqe";

  public String generateToken(String name, String password) {
    return Jwts
      .builder()
      .setSubject(name + ";" + password)
      .setIssuer("techbasket")
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .signWith(
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)),
        SignatureAlgorithm.HS256
      )
      .compact();
  }

  public Claims extract(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
      .build()
      .parseClaimsJws(token)
      .getBody();
  }
}
