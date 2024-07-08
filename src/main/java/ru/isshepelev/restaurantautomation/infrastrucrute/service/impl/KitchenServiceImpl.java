package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.kafka.Producer;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.OrderItem;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Status;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.KitchenService;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    private final OrderService orderService;
    private final Producer producer;
    private List<Order> orders = new ArrayList<>();


    @PostConstruct
    private void addOrdersForList(){
        orders.addAll(orderService.receiveOrdersForStatus(Status.SEND_TO_KITCHEN));
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
    public void removeOrder(String orderId){
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()){
            Order order = iterator.next();
            if (order.getId().toString().equals(orderId)){
                iterator.remove();
                orderService.savePreparedOrder(order);
                sendOrderToHall(order);
            }
        }
    }
    private void sendOrderToHall(Order order){
        producer.sendPreparedOrder(order);
    }
}
