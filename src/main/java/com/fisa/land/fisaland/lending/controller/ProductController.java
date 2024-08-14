package com.fisa.land.fisaland.lending.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("village")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    //물건 등록
    @PostMapping("addProduct")
    public Long addProduct(@RequestBody ProductDTO.CreateProduct createProduct) {
        Long userId = productService.saveProduct(createProduct);
		return userId;

    }
    //등록된 모든 물건 검색
    @GetMapping("products")
    public List<ProductDTO.getProduct> getProducts() {
    	return productService.getProductList();
        
    }
    // 물건 id 로 검색
    @GetMapping("/{productId}")
    public ProductDTO.getProduct getProduct(@PathVariable("productId") Long productId) {
    	return productService.getProduct(productId);
    }
    //대여 신청
    @PatchMapping("/products/{productId}/rent")
    public void rentProduct(@PathVariable("productId") Long productId) {
        productService.updateProductStatus(productId, Product.Status.RENTED);
    }
    //반납 신청
    @PatchMapping("products/{productId}/return")
    public ResponseEntity<String> returnProduct(@PathVariable("productId") Long productId, HttpSession session) {
        // 세션에서 유저 ID를 가져옵니다.
        Long userId = (Long) session.getAttribute("userId");

        try {
            productService.approveReturn(productId, userId);
            return ResponseEntity.ok("반납 승인이 완료되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
    
    //유저ID로 내가 올린 물건들 검색
    @GetMapping("myProducts")
    public List<ProductDTO.getMyProduct> myProducts(HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
    	
    	return productService.getProductsByUserId(userId);
    }
    
    @DeleteMapping("products/{productId}/delete")
    public ResponseEntity<String> cancelProduct(@PathVariable("productId") Long productId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        boolean isCancelled = productService.deleteProduct(productId, userId);
        
        if (isCancelled) {
            return ResponseEntity.ok("Product cancelled successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to cancel this product.");
        }
    }
    
}