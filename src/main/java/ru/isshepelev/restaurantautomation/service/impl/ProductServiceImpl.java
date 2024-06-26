package ru.isshepelev.restaurantautomation.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import ru.isshepelev.restaurantautomation.entity.Product;
import ru.isshepelev.restaurantautomation.repository.ProductRepository;

import ru.isshepelev.restaurantautomation.service.ProductService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void saveProduct(Product product){
        log.info("Saving new {}", product);
        productRepository.save(product);
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
