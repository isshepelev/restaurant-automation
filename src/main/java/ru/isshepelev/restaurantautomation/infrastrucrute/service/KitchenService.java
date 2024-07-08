package ru.isshepelev.restaurantautomation.infrastrucrute.service;

import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

import java.util.List;

public interface KitchenService {

    List<Order> getOrders();


    void addOrder(OrderDto orderDto);


    void removeOrder(String orderId);
}
