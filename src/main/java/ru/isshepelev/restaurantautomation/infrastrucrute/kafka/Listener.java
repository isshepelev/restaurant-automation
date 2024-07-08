package ru.isshepelev.restaurantautomation.infrastrucrute.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.HallService;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderProcessingService;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

@Component
@RequiredArgsConstructor
public class Listener {
    private final OrderProcessingService processingService;
    private final HallService hallService;

    @KafkaListener(topics = "order", groupId = "1", containerFactory = "kafkaListenerContainerFactory")
    public void orderListener(OrderDto orderDto) {
        processingService.processOrder(orderDto);
    }

    @KafkaListener(topics = "prepared", groupId = "1", containerFactory = "preparedOrderKafkaListenerContainerFactory")
    public void preparedOrderListener(Order order) {
        hallService.addPreparedOrder(order);
    }

}
