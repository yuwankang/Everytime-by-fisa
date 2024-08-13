package com.fisa.land.fisaland.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
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
}