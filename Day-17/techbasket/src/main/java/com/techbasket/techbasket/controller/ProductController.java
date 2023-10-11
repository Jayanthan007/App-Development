package com.techbasket.techbasket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techbasket.techbasket.entity.ProductEntity;
import com.techbasket.techbasket.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

     private final ProductService pService;

     @GetMapping("/getMostIntrested")
     public List<ProductEntity> getMostIn() {
          return pService.getMostIn();
     }
}
