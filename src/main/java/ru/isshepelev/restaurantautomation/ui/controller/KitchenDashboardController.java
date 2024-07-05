package ru.isshepelev.restaurantautomation.ui.controller;

import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.KitchenService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/kitchen-dashboard")
public class KitchenDashboardController {
    private final KitchenService kitchenService;

    @GetMapping()
    public String viewDashboard(Model model){
        model.addAttribute("orders", kitchenService.getOrders());
        System.out.println(kitchenService.getOrders());
        return "kitchen-dashboard";
    }
}
