package com.fisa.land.fisaland.lending.service;

import java.util.List;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
import com.fisa.land.fisaland.lending.dto.ProductDTO.getProduct;
import com.fisa.land.fisaland.lending.entity.Product;

public interface ProductService {

    Long saveProduct(ProductDTO.CreateProduct createProduct);

	List<getProduct> getProductList();
	
	ProductDTO.getProduct getProduct(Long productId);
    
	void updateProductStatus(Long productId, Product.Status status);
	
}