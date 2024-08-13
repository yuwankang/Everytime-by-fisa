package com.fisa.fisaland.lending.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fisa.fisaland.lending.entity.Product;
import com.fisa.fisaland.lending.repository.ProductRepository;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 추가적인 비즈니스 로직이 필요한 경우 메서드를 추가할 수 있습니다.
}