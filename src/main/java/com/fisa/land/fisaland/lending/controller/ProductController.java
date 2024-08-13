package com.fisa.land.fisaland.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
import com.fisa.land.fisaland.lending.entity.Product;
import com.fisa.land.fisaland.lending.service.ProductService;


@RestController
@RequestMapping("village")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("addProduct")
    public Long addProduct(@RequestBody ProductDTO.CreateProduct createProduct) {
        Long userId = productService.saveProduct(createProduct);
		return userId;

    }
    
    @GetMapping("products")
    public List<ProductDTO.getProduct> getProducts() {
    	return productService.getProductList();
        
    }
    
    @GetMapping("/{productId}")
    public ProductDTO.getProduct getProduct(@PathVariable("productId") Long productId) {
    	return productService.getProduct(productId);
    }
    
    @PatchMapping("/products/{productId}/rent")
    public void rentProduct(@PathVariable("productId") Long productId) {
        productService.updateProductStatus(productId, Product.Status.RENTED);
    }
    
    @PatchMapping("products/{productId}/return")
    public void returnProduct(@PathVariable("productId") Long productId) {
        productService.updateProductStatus(productId, Product.Status.AVAILABLE);
    }
    
    
}