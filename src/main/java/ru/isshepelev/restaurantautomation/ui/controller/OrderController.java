package ru.isshepelev.restaurantautomation.ui.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;

@Controller
@RequestMapping("/order")
@SessionAttributes("basket")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public String order(HttpSession http){
        orderService.sendOrder(http);
        return "redirect:/menu";
    }



}
