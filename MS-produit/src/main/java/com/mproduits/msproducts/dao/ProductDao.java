package com.mproduits.msproducts.dao;

import com.mproduits.msproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao  extends JpaRepository<Product,Long> {
}
