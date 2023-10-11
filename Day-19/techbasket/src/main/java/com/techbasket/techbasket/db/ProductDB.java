package com.techbasket.techbasket.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techbasket.techbasket.entity.ProductEntity;

@Repository
public interface ProductDB extends CrudRepository<ProductEntity, Integer> {
     @Query(value = "select * from tech_basket.product order by likes desc limit 10", nativeQuery = true)
     public List<ProductEntity> getMostIn();
}
