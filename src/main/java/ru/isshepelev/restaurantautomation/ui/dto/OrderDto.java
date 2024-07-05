package ru.isshepelev.restaurantautomation.ui.dto;

import lombok.Data;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Product;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto implements Serializable{
    private UUID id;
    private Date timestamp;
    private List<OrderItemDto> items;
    private Status status;
    private BigDecimal totalPrice;
    private String individualCode;
}