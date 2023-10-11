package com.techbasket.techbasket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbasket.techbasket.db.ProductDB;
import com.techbasket.techbasket.db.SellerDb;
import com.techbasket.techbasket.db.UserDb;
import com.techbasket.techbasket.entity.ProductEntity;
import com.techbasket.techbasket.entity.SellerEntity;
import com.techbasket.techbasket.entity.UserEntity;

public interface UserService {
     public boolean isHeThere(String username, String password);

     public boolean addMe(UserEntity user);

     public boolean doesSellerExist(String username, String password);

     public boolean addSeller(String username, String password, String country);

     public boolean addProduct(String username, String password, ProductEntity product);
}

@Service
class Implementor implements UserService {

     @Autowired
     UserDb userDb;
     @Autowired
     SellerDb sellerDb;
     @Autowired
     ProductDB productDB;

     @Override
     public boolean isHeThere(String username, String password) {
          return userDb.doesExist(username, password) == 1;
     }

     @Override
     public boolean addMe(UserEntity user) {
          try {
               userDb.save(user);
               return true;
          } catch (Exception err) {
               return false;
          }
     }

     @Override
     public boolean doesSellerExist(String username, String password) {
          return userDb.doesSellerExist(username, password) == 1;
     }

     @Override
     public boolean addSeller(String username, String password, String country) {
          int id = userDb.getUserId(username, password);
          try {
               SellerEntity seller = new SellerEntity();
               seller.setCountry(country);
               seller.setUid(id);
               System.out.print(sellerDb);
               sellerDb.save(seller);
               return true;
          } catch (Exception err) {
               System.out.println(err);
               return false;
          }
     }

     @Override
     public boolean addProduct(String username, String password, ProductEntity product) {
          int uid = userDb.getUserId(username, password);
          int sid = sellerDb.getSellerId(uid);
          try {
               product.setSid(sid);
               productDB.save(product);
               return true;
          } catch (Exception err) {
               return false;
          }
     }

}
