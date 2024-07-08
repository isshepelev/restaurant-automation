package ru.isshepelev.restaurantautomation.infrastrucrute.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, Object> template;

    public void sendOrder(OrderDto order){
        template.send("order", order);
    }
    public void sendPreparedOrder(Order preparedOrder){
        template.send("prepared", preparedOrder);
    }
}
