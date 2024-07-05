package ru.isshepelev.restaurantautomation.infrastrucrute.service;

import jakarta.servlet.http.HttpSession;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

import java.util.List;

public interface OrderService {

    String sendOrder(HttpSession http);

    void saveOrder(OrderDto orderDto);

    List<Order> receiveOrdersSentToKitchen();
}
