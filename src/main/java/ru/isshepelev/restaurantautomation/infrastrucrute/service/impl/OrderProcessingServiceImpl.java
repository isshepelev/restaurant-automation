package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderProcessingService;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class OrderProcessingServiceImpl implements OrderProcessingService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final OrderService orderService;


    @Override
    public void processOrder(OrderDto orderDto){
        executorService.submit(() -> {
            orderService.saveOrder(orderDto);
            //TODO добавить отправку на кухню
        });
    }
}
