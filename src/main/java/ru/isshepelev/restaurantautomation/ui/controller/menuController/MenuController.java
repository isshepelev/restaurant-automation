package ru.isshepelev.restaurantautomation.ui.controller.menuController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Product;
import ru.isshepelev.restaurantautomation.infrastrucrute.service.ProductService;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final ProductService productService;

    @GetMapping("/menu")
    public String menu(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "menu";
    }

}
