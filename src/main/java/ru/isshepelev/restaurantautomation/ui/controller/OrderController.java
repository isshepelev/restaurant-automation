package ru.isshepelev.restaurantautomation.ui.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.OrderService;

@Controller
@RequestMapping("/order")
@SessionAttributes("basket")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public String order(HttpSession http, RedirectAttributes redirectAttributes) {
        try {
            orderService.sendOrder(http);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/basket";
        }
        return "redirect:/menu";
    }



}
