package ru.isshepelev.restaurantautomation.infrastrucrute.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, OrderDto> template;

    public void sendOrder(OrderDto order){
        template.send("order", order);
    }
}
