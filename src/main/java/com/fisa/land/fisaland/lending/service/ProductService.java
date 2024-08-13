package com.fisa.land.fisaland.lending.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
import com.fisa.land.fisaland.lending.entity.Product;
import com.fisa.land.fisaland.lending.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HttpSession httpSession;

    public Long saveProduct(ProductDTO.CreateProduct createProduct) {
    	Long userId = (Long) httpSession.getAttribute("userId");

        Product product = new Product();
        product.setProduct_name(createProduct.getProductName());
        product.setPrice(createProduct.getPrice());
        product.setCategory(createProduct.getCategory());
        product.setStatus(Product.Status.AVAILABLE);
        product.setUser_id(userId);

        Product savedProduct =productRepository.save(product);
        
        return savedProduct.getUser_id();
    }
}