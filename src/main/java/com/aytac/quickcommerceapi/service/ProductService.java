package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.ProductDto;
import com.aytac.quickcommerceapi.dto.converter.ProductDtoConverter;
import com.aytac.quickcommerceapi.dto.request.ProductCreateRequest;
import com.aytac.quickcommerceapi.exception.custom.ProductNotFoundException;
import com.aytac.quickcommerceapi.model.Product;
import com.aytac.quickcommerceapi.model.Subcategory;
import com.aytac.quickcommerceapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoConverter converter;
    private final SubcategoryService subcategoryService;

    public ProductService(ProductRepository productRepository,
                          ProductDtoConverter converter,
                          SubcategoryService subcategoryService) {
        this.productRepository = productRepository;
        this.converter = converter;
        this.subcategoryService = subcategoryService;
    }

    public Product findProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found by id= " + productId));
    }

    public ProductDto getProductById(Long productId) {

        Optional<Product> product = productRepository.findById(productId);

        return product.map(converter::convertToProductDto).orElseThrow(
                () -> new ProductNotFoundException("Product not found by id= " + productId)); //product not found exception
    }

    public ProductDto createProduct(ProductCreateRequest request) {

        Subcategory subcategory = subcategoryService.getSubcategoryById(request.subcategoryId());

        Product toSave = new Product();
        toSave.setTitle(request.title());
        toSave.setShortTitle(request.shortTitle());
        toSave.setSubcategory(subcategory);
        toSave.setPrice(request.price());
        toSave.setAltText(request.altText());
        toSave.setImageData(request.imageData());

        productRepository.save(toSave);

        return converter.convertToProductDto(toSave);
    }
}