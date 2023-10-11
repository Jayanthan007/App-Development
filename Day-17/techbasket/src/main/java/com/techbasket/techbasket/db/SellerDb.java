package com.techbasket.techbasket.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techbasket.techbasket.entity.SellerEntity;

@Repository
public interface SellerDb extends CrudRepository<SellerEntity, Integer> {

     @Query(value = "select sellerid from seller where uid = ?1 limit 1", nativeQuery = true)
     public int getSellerId(int uid);
}
