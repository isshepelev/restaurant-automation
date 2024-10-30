package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Basket;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Product;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.BasketService;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.ProductService;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final ProductService productService;


    @Override
    public Basket viewBasket(HttpSession http){
        Basket basket = (Basket) http.getAttribute("basket");

        if (basket == null){
            basket = new Basket();
            http.setAttribute("basket", basket);
        }
        return basket;
    }

    @Override
    public void addToBasket(Long productId, HttpSession http){
        Basket basket = (Basket) http.getAttribute("basket");
        if (basket == null){
            basket = new Basket();
            http.setAttribute("basket", basket);
        }
        basket.addProduct(productService.getProductById(productId)); 
    }


    @Override
    public void removeProduct(Long productId, HttpSession http){
        Basket basket = (Basket) http.getAttribute("basket");
        if (basket != null){
            basket.removeProduct(productService.getProductById(productId));
        }
    }

    @Override
    public void clearBasket(HttpSession http){
        Basket basket = (Basket) http.getAttribute("basket");
        if (basket != null){
            basket.clearBasket();
        }
    }
}
