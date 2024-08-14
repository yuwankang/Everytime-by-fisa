package com.fisa.land.fisaland.lending.service;

import java.util.List;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
import com.fisa.land.fisaland.lending.dto.ProductDTO.getProduct;
import com.fisa.land.fisaland.lending.entity.Product;

public interface ProductService {
	
	
	//물건 등록
    Long saveProduct(ProductDTO.CreateProduct createProduct);

    
    //모든 물건 검색
	List<getProduct> getProductList();
	
	
	//물건id 로 검색
	ProductDTO.getProduct getProduct(Long productId);
    
	//대여 신청
	void updateProductStatus(Long productId, Product.Status status);
	
	//반납 허옹
	void approveReturn(Long productId, Long userId);
	
	//userId로 내가올린 물건들 검색
	List<ProductDTO.getMyProduct> getProductsByUserId(Long userId);
	
	boolean deleteProduct(Long productId, Long userId);
	
}