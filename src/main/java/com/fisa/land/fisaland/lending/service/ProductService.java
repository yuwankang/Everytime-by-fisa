package com.fisa.land.fisaland.lending.service;

import java.util.List;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
import com.fisa.land.fisaland.lending.dto.ProductDTO.getProduct;

public interface ProductService {

    Long saveProduct(ProductDTO.CreateProduct createProduct);

	List<getProduct> getProductList();
    
}