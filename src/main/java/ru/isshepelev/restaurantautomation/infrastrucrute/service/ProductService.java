package ru.isshepelev.restaurantautomation.infrastrucrute.service;

import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    Product getProductById(Long id) ;

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    boolean checkAndReduceStock(Product product, int quantity);
}
