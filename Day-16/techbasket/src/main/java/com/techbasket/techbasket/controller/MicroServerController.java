package com.techbasket.techbasket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MicroServerController {

     private static String comparisionServerLink = "http://localhost:8081";

    RestTemplate restTemplate = new RestTemplate();

     @GetMapping("/checkHim")
     public ResponseEntity<Object> viewAllFeedBacks() {
          return restTemplate.getForEntity(comparisionServerLink + "/checkHim", Object.class);
     }
}
