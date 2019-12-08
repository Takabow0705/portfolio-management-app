package app.component.controller.product.bond;

import app.commons.entities.BondMaster;
import app.component.service.bondMaster.BondMasterManagementService;
import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/management/products/bond")
public class BondMasterManagementController {

    /** */
    @Autowired
    @Qualifier("bondMasterManagementService")
    private BondMasterManagementService bondMasterManagementService;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @GetMapping
    public String index(Model model){
        List<BondMaster> bondList = bondMasterManagementService.findAll();
        model.addAttribute("bondList",bondList);
        logger.atInfo().log("以下の債券情報を取得しました。：%s",bondList);
        return "/management/products/bond/index";
    }
}
