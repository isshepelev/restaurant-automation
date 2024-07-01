package ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity;

import lombok.Data;
import ru.isshepelev.restaurantautomation.ui.dto.OrderItemDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class Basket implements Serializable {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        products.merge(product, 1, Integer::sum);
    }

    public void removeProduct(Product product) {
        products.computeIfPresent(product, (key, quantity) -> quantity > 1 ? quantity - 1 : null);
    }

    public void clearBasket() {
        products.clear();
    }

    public List<OrderItemDto> getOrderItems() {
        return products.entrySet().stream()
                .map(entry -> {
                    OrderItemDto orderItem = new OrderItemDto();
                    orderItem.setProduct(entry.getKey());
                    orderItem.setQuantity(entry.getValue());
                    orderItem.setPrice(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
                    return orderItem;
                })
                .collect(Collectors.toList());
    }
}