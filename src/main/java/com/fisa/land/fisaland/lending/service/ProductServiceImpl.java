package com.fisa.land.fisaland.lending.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
import com.fisa.land.fisaland.lending.dto.ProductDTO.getProduct;
import com.fisa.land.fisaland.lending.entity.Product;
import com.fisa.land.fisaland.lending.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private HttpSession httpSession;

    @Transactional
	@Override
	public Long saveProduct(ProductDTO.CreateProduct createProduct) {
		Long userId = (Long) httpSession.getAttribute("userId");
		
		        Product product = new Product();
		        product.setProductName(createProduct.getProductName());
		        product.setDescription(createProduct.getDescription());
		        product.setPrice(createProduct.getPrice());
		        product.setCategory(createProduct.getCategory());
		        product.setStatus(Product.Status.AVAILABLE);
		        product.setUserId(userId);
		
		        Product savedProduct = productRepository.save(product);
		        
		        return savedProduct.getUserId();
		    }

	@Override
	public List<getProduct> getProductList() {
		List<ProductDTO.getProduct> products =  productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, ProductDTO.getProduct.class))
				.collect(Collectors.toList());
		
		return products;

	}
	
	public ProductDTO.getProduct getProduct(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 product id입니다"));
		return modelMapper.map(product, ProductDTO.getProduct.class);
	}
	
	@Override
    public void updateProductStatus(Long productId, Product.Status status) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        if (product.getStatus().equals(Product.Status.RENTED)) {
            throw new IllegalStateException("이미 대여 중인 물건입니다.");
        }
        
        product.setStatus(status);
        productRepository.save(product);
    }
	
	@Override
    public void approveReturn(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        // 유저 ID 비교
        if (!product.getUserId().equals(userId)) {
            throw new IllegalStateException("물건의 주인이 아닙니다");
        }
        // 물건의 상태가 RENTED가 아니면 반납 승인 불가
        if (!product.getStatus().equals(Product.Status.RENTED)) {
            throw new IllegalStateException("반납할 수 없는 상태입니다.");
        }

        // 주인이 맞다면 상태를 AVAILABLE로 변경
        product.setStatus(Product.Status.AVAILABLE);
        productRepository.save(product);
    }
	
	@Override
    public List<ProductDTO.getMyProduct> getProductsByUserId(Long userId) {
        List<Product> products = productRepository.findByUserId(userId); // 사용자 ID로 제품 조회
        return products.stream()
                       .map(this::convertToDTO)
                       .collect(Collectors.toList());
    }

    private ProductDTO.getMyProduct convertToDTO(Product product) {
        ProductDTO.getMyProduct dto = new ProductDTO.getMyProduct();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName()); 
        dto.setDescription(product.getDescription());
        dto.setStatus(product.getStatus());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setCreatedAt(product.getCreatedAt());  // 추가된 필드
        return dto;
    }
    
    @Override
    public boolean deleteProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        // 확인: 사용자가 이 상품의 소유자인지 체크
        if (!product.getUserId().equals(userId)) {
            return false; 
        }

        productRepository.delete(product); // 상품 삭제
        return true;
    }
}