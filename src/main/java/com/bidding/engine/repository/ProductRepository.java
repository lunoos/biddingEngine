package com.bidding.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bidding.engine.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByVendorId(Long vendorId);
}