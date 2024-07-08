package ru.isshepelev.restaurantautomation.infrastrucrute.service;

import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;

import java.util.List;

public interface HallService {

    void addPreparedOrder(Order order);

    List<Order> getPreparedOrders();
}
