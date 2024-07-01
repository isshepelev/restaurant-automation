package ru.isshepelev.restaurantautomation.infrastrucrute.service;

import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

public interface OrderProcessingService {

    void processOrder(OrderDto orderDto);
}
