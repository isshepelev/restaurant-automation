package ru.isshepelev.restaurantautomation.ui.dto;

import lombok.Data;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Product;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDto implements Serializable {
    private Product product;
    private int quantity;
    private BigDecimal price;
}