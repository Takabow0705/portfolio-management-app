package app.presentation.controller.calculator.pricer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calc/price")
public class PricingController{

    @GetMapping
    public String index(Model model){
        return "calc/price/index";
    }
}