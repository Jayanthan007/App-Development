package com.techbasket.techbasket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbasket.techbasket.db.ProductDB;
import com.techbasket.techbasket.entity.ProductEntity;

@Service
public class ProductService {

     @Autowired
     ProductDB productDB;

     public Iterable<ProductEntity> getAll() {
          return productDB.findAll();
     }

     public List<ProductEntity> getMostIn(){
          return productDB.getMostIn();
     }

}
