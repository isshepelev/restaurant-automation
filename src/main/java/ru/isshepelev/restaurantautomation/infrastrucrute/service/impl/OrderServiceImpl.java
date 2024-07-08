package ru.isshepelev.restaurantautomation.infrastrucrute.service.impl;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isshepelev.restaurantautomation.infrastrucrute.kafka.Producer;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.*;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.repository.OrderRepository;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.ProductService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderDto;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;
import ru.isshepelev.restaurantautomation.ui.dto.OrderItemDto;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final Producer producer;
    private final OrderRepository orderRepository;
    private final ProductService productService;


    @Override
    public String sendOrder(HttpSession http) {
        Basket basket = (Basket) http.getAttribute("basket");
        if (basket == null) {
            throw new NullPointerException("Basket is empty");
        }
        for (Map.Entry<Product, Integer> entry : basket.getProducts().entrySet()){
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (!productService.checkAndReduceStock(product, quantity)) {
                throw new IllegalArgumentException("Столько товара нет: " + product.getName() + ", максимум " + product.getCount());
            }
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setId(UUID.randomUUID());
        orderDto.setTimestamp(new Date());
        orderDto.setItems(basket.getOrderItems());
        orderDto.setStatus(Status.SEND_TO_KITCHEN);
        orderDto.setIndividualCode(generateIndividualCode());
        orderDto.setTotalPrice(basket.getOrderItems().stream()
                .map(OrderItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        producer.sendOrder(orderDto);
        basket.clearBasket();
        return orderDto.getIndividualCode();
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
        order.setId(orderDto.getId());
        order.setOrderStatus(orderDto.getStatus());
        order.setTimestamp(orderDto.getTimestamp());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setIndividualCode(orderDto.getIndividualCode());
        orderRepository.save(order);
    }
    @Override
    public void savePreparedOrder(Order order){
        order.setOrderStatus(Status.PREPARED);
        orderRepository.save(order);
    }
    @Override
    public void saveCompletedOrder(Order order){
        order.setOrderStatus(Status.COMPLETED);
        orderRepository.save(order);
    }

    private String generateIndividualCode() {
        int leftLimit = 48; // цифра 0
        int targetStringLength = 4;
        Random random = new Random();

        String code = random.ints(leftLimit, leftLimit + 9)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return code;
    }

    @Override
    @Transactional()
    public List<Order> receiveOrdersForStatus(Status status){
        List<Order> orders = orderRepository.findByOrderStatus(status);
        orders.forEach(order -> order.getItems().size()); // нужно для инициализации ленивых коллекций
        return orders;

    }
}