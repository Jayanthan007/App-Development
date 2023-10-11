package com.techbasket.techbasket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seller")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SellerEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int sellerid;
     private String country;
     private int uid;
}
