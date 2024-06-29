package ru.isshepelev.restaurantautomation.infrastrucrute.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Basket;

public interface BasketService {

    Basket viewBasket(HttpSession http);

    void addToBasket(Long productId, HttpSession http);

    void removeProduct(Long productId, HttpSession http);

    void clearBasket(HttpSession http);
}
