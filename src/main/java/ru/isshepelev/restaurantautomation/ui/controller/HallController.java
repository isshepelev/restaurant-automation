package ru.isshepelev.restaurantautomation.ui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.HallService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hall")
public class HallController {
    private final HallService hallService;

    @GetMapping
    public String viewPreparedOrders(Model model){
        model.addAttribute("orders", hallService.getPreparedOrders());
        return "hall";
    }
}
