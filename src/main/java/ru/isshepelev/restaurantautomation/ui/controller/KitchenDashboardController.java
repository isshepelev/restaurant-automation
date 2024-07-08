package ru.isshepelev.restaurantautomation.ui.controller;

import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "kitchen-dashboard";
    }
    @PostMapping("/mark-as-prepared/{orderId}")
    public String prepared(@PathVariable String orderId){
        kitchenService.removeOrder(orderId);
        return "redirect:/kitchen-dashboard";
    }
}
