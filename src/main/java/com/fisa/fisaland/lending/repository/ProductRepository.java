package com.fisa.fisaland.lending.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fisa.fisaland.lending.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 필요한 경우 커스텀 쿼리 메서드를 추가할 수 있습니다.
}