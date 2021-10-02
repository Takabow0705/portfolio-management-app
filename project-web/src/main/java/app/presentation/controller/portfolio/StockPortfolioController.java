package app.presentation.controller.portfolio;

import app.domain.auth.UserAuthInfo;
import app.domain.portfolio.StockPortfolioReferenceDto;
import app.usecase.portfolio.StockPortfolioService;
import com.google.common.flogger.FluentLogger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/management/portfolio/stock")
public class StockPortfolioController {

    private final StockPortfolioService stockPortfolioService;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public StockPortfolioController(StockPortfolioService stockPortfolioService){
        this.stockPortfolioService = stockPortfolioService;
    }

    /**
     * <p>
     *     株式ポートフォリオ管理画面トップ
     * </p>
     *
     * @param model
     * @return ポートフォリオ情報画面
     */
    @GetMapping
    public String index(@AuthenticationPrincipal UserAuthInfo session, Model model){
        if (session == null){
            throw new SessionAuthenticationException("Session Info is null");
        }
        Long userId = session.getUserId();
        List<StockPortfolioReferenceDto> lists = this.stockPortfolioService.findByUserId(userId);
        model.addAttribute("portfolio", lists);
        return "management/portfolio/stock/index";
    }
}
