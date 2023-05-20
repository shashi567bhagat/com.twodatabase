package com.spring.database.Mysql_2.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.database.Mysql_2.Entity.Product;

public interface ProductRepo  extends JpaRepository<Product, Integer>{

}
