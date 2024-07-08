package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Status;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.HallService;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class HallServiceImpl implements HallService {
    private final OrderService orderService;
    private List<Order> preparedOrders = new ArrayList<>();

    @PostConstruct
    private void addPreparedOrdersForList(){
        preparedOrders.addAll(orderService.receiveOrdersForStatus(Status.PREPARED));
    }
    @Override
    public void addPreparedOrder(Order order){
        preparedOrders.add(order);
    }
    @Override
    public List<Order> getPreparedOrders(){
        return preparedOrders;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 10000)
    private void removePreparedOrder(){
        if (!preparedOrders.isEmpty()){
            Order order = preparedOrders.remove(0);
            orderService.saveCompletedOrder(order);
        }
    }
}
