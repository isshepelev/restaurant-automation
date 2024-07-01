package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.kafka.Producer;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Basket;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.OrderItem;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.repository.OrderRepository;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderItemDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final Producer producer;
    private final OrderRepository orderRepository;

    @Override
    public void sendOrder(HttpSession http) {
        Basket basket = (Basket) http.getAttribute("basket");
        if (basket == null) {
            throw new NullPointerException("Basket is empty");
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setTimestamp(new Date());
        orderDto.setItems(basket.getOrderItems());
        orderDto.setPrepared(false);
        orderDto.setTotalPrice(basket.getOrderItems().stream()
                .map(OrderItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        producer.sendOrder(orderDto);
        basket.clearBasket();
    }

    @Override
    public void saveOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setItems(orderDto.getItems().stream().map(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(orderItemDto.getProduct());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setPrice(orderItemDto.getPrice());
            return orderItem;
        }).collect(Collectors.toList()));
        order.setTimestamp(orderDto.getTimestamp());
        order.setTotalPrice(orderDto.getTotalPrice());
        orderRepository.save(order);
    }
}