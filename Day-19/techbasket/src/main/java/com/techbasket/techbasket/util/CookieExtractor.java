package com.techbasket.techbasket.util;

import org.springframework.stereotype.Component;

@Component
public class CookieExtractor {

     public String extractCookie(String cookie, String cName) {
          String c = null;
          String cookies[] = cookie.split(";");
          for (var a : cookies) {
               if (a.startsWith(!cName.equals(null) ? cName : "user-auth-key")) {
                    c = a.split("=")[1];
                    break;
               }
          }
          return c;
     }

}
