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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/village")
@Tag(name = "대여 물품 API", description = "물품 등록, 조회, 대여 및 반납 API")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "물품 등록", description = "새로운 물품을 등록하는 API")
    @PostMapping("/addProduct")
    public Long addProduct(@RequestBody ProductDTO.CreateProduct createProduct) {
        return productService.saveProduct(createProduct);
    }

    @Operation(summary = "모든 물품 조회", description = "등록된 모든 물품을 조회하는 API")
    @GetMapping("/products")
    public List<ProductDTO.getProduct> getProducts() {
        return productService.getProductList();
    }

    @Operation(summary = "물품 상세 조회", description = "물품 ID로 특정 물품을 조회하는 API")
    @GetMapping("/products/{productId}")
    public ProductDTO.getProduct getProduct(
            @Parameter(description = "물품 ID", example = "1") @PathVariable("productId") Long productId) {
        return productService.getProduct(productId);
    }

    @Operation(summary = "대여 신청", description = "특정 물품을 대여 신청하는 API")
    @PatchMapping("/products/{productId}/rent")
    public void rentProduct(
            @Parameter(description = "물품 ID", example = "1") @PathVariable("productId") Long productId) {
        productService.updateProductStatus(productId, Product.Status.RENTED);
    }

    @Operation(summary = "반납 신청", description = "대여한 물품을 반납 신청하는 API")
    @PatchMapping("/products/{productId}/return")
    public ResponseEntity<String> returnProduct(
            @Parameter(description = "물품 ID", example = "1") @PathVariable("productId") Long productId,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        try {
            productService.approveReturn(productId, userId);
            return ResponseEntity.ok("반납 승인이 완료되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Operation(summary = "내가 등록한 물품 조회", description = "세션에 저장된 유저 ID로 내가 등록한 물품을 조회하는 API")
    @GetMapping("/myProducts")
    public List<ProductDTO.getMyProduct> myProducts(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return productService.getProductsByUserId(userId);
    }

    @Operation(summary = "물품 삭제", description = "특정 물품을 삭제하는 API")
    @DeleteMapping("/products/{productId}/delete")
    public ResponseEntity<String> cancelProduct(
            @Parameter(description = "물품 ID", example = "1") @PathVariable("productId") Long productId,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        boolean isCancelled = productService.deleteProduct(productId, userId);

        if (isCancelled) {
            return ResponseEntity.ok("물품이 성공적으로 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("이 물품을 삭제할 권한이 없습니다.");
        }
    }
}
