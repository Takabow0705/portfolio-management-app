package app.presentation.controller.product.currency;


import app.usecase.products.currencyMaster.CurrencyMasterManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.infra.rdb.currencymaster.CurrencyMaster;

import java.util.List;

@Controller
@RequestMapping("/management/products/currency")
public class CurrencyMasterManagementController {

    @Autowired
    @Qualifier("currencyMasterManagementService")
    private CurrencyMasterManagementService currencyMasterManagementService;

    @GetMapping
    public String index(Model model){
        List<CurrencyMaster> currencyList = this.currencyMasterManagementService.findAll();
        model.addAttribute("currencyList", currencyList);
        return "management/products/currency/index";
    }
}
