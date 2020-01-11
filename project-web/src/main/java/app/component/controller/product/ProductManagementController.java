package app.component.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/products")
public class ProductManagementController {

    @GetMapping
    public String index(Model model){
        return "management/products/index";
    }
}
