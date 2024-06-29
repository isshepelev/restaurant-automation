package ru.isshepelev.restaurantautomation.ui.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.BasketService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
@SessionAttributes("basket")
public class BasketController {
    private final BasketService basketService;


    @GetMapping
    public String viewBasket(Model model, HttpSession http){
        model.addAttribute("basket", basketService.viewBasket(http));
        return "basket";
    }

    @PostMapping("/addToBasket/{productId}")
    public String addToBasket(@PathVariable Long productId, HttpSession http){
        basketService.addToBasket(productId,http);
        return "redirect:/menu";
    }

    @PostMapping("/remove/{productId}")
    public String removeProduct(@PathVariable Long productId, HttpSession http){
        basketService.removeProduct(productId,http);
        return "redirect:/basket";
    }

    @PostMapping("/clear")
    public String clearBasket(HttpSession http){
        basketService.clearBasket(http);
        return "redirect:/basket";
    }

}
