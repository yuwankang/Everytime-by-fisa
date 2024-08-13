package com.fisa.land.fisaland.lending.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.land.fisaland.lending.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}