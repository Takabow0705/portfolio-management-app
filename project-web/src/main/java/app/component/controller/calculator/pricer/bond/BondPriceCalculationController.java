package app.component.controller.calculator.pricer.bond;

import app.commons.dto.BondDataDto;
import app.commons.enums.BondPricingAlgorithmsEnum;
import app.component.service.pricer.bond.BondPriceCalculationService;
import com.google.common.flogger.FluentLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calc/price/bond")
public class BondPriceCalculationController {

    private final BondPriceCalculationService bondPriceCalculationService;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public BondPriceCalculationController(BondPriceCalculationService bondPriceCalculationService) {
        this.bondPriceCalculationService = bondPriceCalculationService;
    }


    /**
     * <p>
     *     債券価格計算画面で最初に出てくる画面
     * </p>
     *
     * @param model
     * @return 債券価格計算画面
     */
    @GetMapping
    public String index(Model model){
        model.addAttribute("form", new BondDataDto());
        model.addAttribute("algorithms", BondPricingAlgorithmsEnum.values());
        return "/calc/price/bond/index";
    }

    /**
     * <p>
     *     理論価格計算結果を表示する。
     * </p>
     *
     * @param bondDataDto
     * @return 債券価格計算画面
     */
    @PostMapping("execute")
    public String calculateBondPriceByDiscountFactor(@ModelAttribute BondDataDto bondDataDto, Model model){
        logger.atInfo().log("以下のDTOを送信: %s", bondDataDto);
        BondDataDto result = this.bondPriceCalculationService.calculateBondPriceByDiscountFactor(bondDataDto);

        if( result == null ){
            model.addAttribute("form", bondDataDto);
            model.addAttribute("algorithms", BondPricingAlgorithmsEnum.values());
            return "/calc/price/bond/index";
        }

        bondDataDto.setTheoreticalPrice(result.getTheoreticalPrice());
        model.addAttribute("form", bondDataDto);
        model.addAttribute("algorithms", BondPricingAlgorithmsEnum.values());
        return "/calc/price/bond/index";
    }
}
