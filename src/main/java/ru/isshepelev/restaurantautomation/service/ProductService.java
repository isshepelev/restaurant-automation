package ru.isshepelev.restaurantautomation.service;

import org.springframework.web.multipart.MultipartFile;
import ru.isshepelev.restaurantautomation.entity.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    Product getProductById(Long id) ;

    List<Product> getAllProducts();

    void deleteProduct(Long id);
}
