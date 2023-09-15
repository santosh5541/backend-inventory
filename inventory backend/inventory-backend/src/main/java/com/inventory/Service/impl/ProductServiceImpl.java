package com.inventory.Service.impl;

import com.inventory.Repository.ProductRepository;
import com.inventory.Service.ProductService;
import com.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }
}
