package com.techbasket.techbasket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int pid;
     private String name;
     private String category;
     private int likes = 0;
     private int dislike = 0;
     private String price;
     private int sid;
     @Column(columnDefinition = "LONGTEXT")
     private String img;
}
