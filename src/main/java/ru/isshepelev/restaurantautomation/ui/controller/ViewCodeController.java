package ru.isshepelev.restaurantautomation.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/code")
public class ViewCodeController {

    @GetMapping("/{code}")
    public String viewCode(Model model, @PathVariable String code){
        model.addAttribute("code", code);
        return "view-code";
    }

}
