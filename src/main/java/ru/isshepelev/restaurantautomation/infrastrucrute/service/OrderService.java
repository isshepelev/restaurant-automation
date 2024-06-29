package ru.isshepelev.restaurantautomation.infrastrucrute.service;

import jakarta.servlet.http.HttpSession;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

public interface OrderService {

    void sendOrder(HttpSession http);

    void saveOrder(OrderDto orderDto);
}
