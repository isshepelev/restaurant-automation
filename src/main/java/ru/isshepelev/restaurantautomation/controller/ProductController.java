package ru.isshepelev.restaurantautomation.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.isshepelev.restaurantautomation.entity.Product;
import ru.isshepelev.restaurantautomation.service.ProductService;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product/create")
    public String createProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }
    @GetMapping("/products")
    public String allProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
