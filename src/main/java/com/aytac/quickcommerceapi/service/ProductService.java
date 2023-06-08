package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.ProductDto;
import com.aytac.quickcommerceapi.dto.converter.ProductDtoConverter;
import com.aytac.quickcommerceapi.exception.ProductNotFoundException;
import com.aytac.quickcommerceapi.model.Product;
import com.aytac.quickcommerceapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoConverter converter;

    public ProductService(ProductRepository productRepository, ProductDtoConverter converter) {
        this.productRepository = productRepository;
        this.converter = converter;
    }

    public ProductDto getProductById(Long productId) {

        Optional<Product> product = productRepository.findById(productId);

        return product.map(converter::convertToProductDto).orElseThrow(
                () -> new ProductNotFoundException("Product not found by id= "+productId)); //product not found exception
    }
}
