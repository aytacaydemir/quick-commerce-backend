package com.aytac.quickcommerceapi.service;

import com.aytac.quickcommerceapi.dto.converter.ProductResponseConverter;
import com.aytac.quickcommerceapi.dto.request.ProductCreateRequest;
import com.aytac.quickcommerceapi.dto.response.ProductResponse;
import com.aytac.quickcommerceapi.model.Product;
import com.aytac.quickcommerceapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductResponseConverter converter;
    private final SubcategoryService subcategoryService;

    public ProductService(ProductRepository productRepository,
                          ProductResponseConverter converter,
                          SubcategoryService subcategoryService) {
        this.productRepository = productRepository;
        this.subcategoryService = subcategoryService;
        this.converter = converter;
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductResponse getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(converter::convert).orElse(null); //ex
    }

    public ProductResponse createProduct(ProductCreateRequest request, Long subcategoryId) {
        Product product = new Product();
        product.setTitle(request.title());
        product.setAltText(request.altText());
        product.setImageData(request.imageData());
        product.setPrice(request.price());
        product.setSubcategory(subcategoryService.findSubcategoryById(subcategoryId));
        productRepository.save(product);

        return converter.convert(product);
    }

}
