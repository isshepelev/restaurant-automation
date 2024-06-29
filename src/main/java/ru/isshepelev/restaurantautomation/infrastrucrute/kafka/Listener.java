package ru.isshepelev.restaurantautomation.infrastrucrute.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

@Component
@RequiredArgsConstructor
public class Listener {
    private final OrderService orderService;
    @KafkaListener(topics = "order", groupId = "1")
    public OrderDto orderLister(OrderDto orderDto){
        orderService.saveOrder(orderDto);
        return orderDto;
    }

}