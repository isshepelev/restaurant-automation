package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.kafka.Producer;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Basket;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.repository.OrderRepository;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Product;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
     private final Producer producer;
     private final OrderRepository orderRepository;

    @Override
    public void sendOrder(HttpSession http) {
        Basket basket = (Basket) http.getAttribute("basket");
        if (basket == null){
            throw new NullPointerException("Basket is empty");
        }
        OrderDto order = new OrderDto();
        order.setTimestamp(new Date());
        order.setProducts(basket.getProducts());
        order.setPrepared(false);
        order.setTotalPrice(basket.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        producer.sendOrder(order);
        basket.clearBasket();
        
    }

    @Override
    public void saveOrder(OrderDto orderDto){
        Order order = new Order();
        order.setProducts(orderDto.getProducts());
        order.setTimestamp(orderDto.getTimestamp());
        order.setTotalPrice(orderDto.getTotalPrice());
        orderRepository.save(order);
    }
}
