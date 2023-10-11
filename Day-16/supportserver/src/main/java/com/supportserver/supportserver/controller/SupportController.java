package com.supportserver.supportserver.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supportserver.supportserver.entity.Alive;

@RestController
public class SupportController {
     @GetMapping("/checkHim")
     public ResponseEntity<Object> checkMe(){
          
          return ResponseEntity.ok().body(Arrays.asList(new Alive("Yes,Im alive.")));
     }
}
