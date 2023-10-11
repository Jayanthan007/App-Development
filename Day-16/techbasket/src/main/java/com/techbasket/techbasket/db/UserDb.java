package com.techbasket.techbasket.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techbasket.techbasket.entity.UserEntity;

@Repository
public interface UserDb extends CrudRepository<UserEntity, Integer> {

     @Query(value = "select (select count(*) FROM user WHERE username = ?1 and password = ?2) > 0 as data", nativeQuery = true)
     public int doesExist(String username, String password);

     @Query(value = "select (select count(*) from seller where uid = (select uid from user where username = ?1 and password = ?2)) > 0 as data", nativeQuery = true)
     public int doesSellerExist(String username, String password);

     @Query(value = "select uid from user where username = ?1 and password = ?2 limit 1", nativeQuery = true)
     public int getUserId(String username, String password);
}
