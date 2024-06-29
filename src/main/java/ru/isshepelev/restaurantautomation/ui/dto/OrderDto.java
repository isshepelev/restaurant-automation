package ru.isshepelev.restaurantautomation.ui.dto;

import lombok.Data;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto implements Serializable{
    private Date timestamp;
    private List<Product> products;
    private BigDecimal totalPrice;
    private boolean isPrepared;
}
