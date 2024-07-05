package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.OrderItem;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.KitchenService;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    private final OrderService orderService;
    private List<Order> orders = new ArrayList<>(); // когда тут сохраняется до рестарта заказы не имеют уникального id надо постоянно обращаться в базу чтобы получать их id


    @PostConstruct
    private void addOrdersForList(){
        orders.addAll(orderService.receiveOrdersSentToKitchen());
    }

    @Override
    public List<Order> getOrders(){
        return orders;
    }

    @Override
    public void addOrder(OrderDto orderDto){
        Order order = new Order();
        order.setItems(orderDto.getItems().stream().map(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(orderItemDto.getProduct());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setPrice(orderItemDto.getPrice());
            return orderItem;
        }).collect(Collectors.toList()));
        order.setId(orderDto.getId());
        order.setOrderStatus(orderDto.getStatus());
        order.setTimestamp(orderDto.getTimestamp());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setIndividualCode(orderDto.getIndividualCode());
        orders.add(order);
    }
    @Override
    public void removeOrder(Order order){
        orders.remove(order);
    }
}
