package app.presentation.controller.product.stock;

import app.domain.product.stockmaster.SearchCondition;
import app.usecase.products.stockMaster.StockMasterManagementService;
import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.infra.rdb.stockmaster.StockMaster;

import java.util.List;

@Controller
@RequestMapping("/management/products/stock/ja")
public class StockMasterManagementController {

    @Autowired
    @Qualifier("stockMasterManagementService")
    private StockMasterManagementService stockMasterManagementService;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @GetMapping
    public String index(Model model){
        List<StockMaster> stockMasterList = this.stockMasterManagementService.findAll();
        model.addAttribute("searchCondition",new SearchCondition());
        model.addAttribute("stocklist",stockMasterList);
        return "management/products/stock/ja/index";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute SearchCondition condition){
        logger.atInfo().log("以下の検索条件を受信しました。",condition.toString());
        List<StockMaster> stockMasterList = this.stockMasterManagementService.findByCondition(condition);
        model.addAttribute("searchCondition", condition);
        model.addAttribute("stocklist",stockMasterList);
        return "management/products/stock/ja/index";
    }
}
