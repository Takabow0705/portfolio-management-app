package app.presentation.api.portfolio.execution;

import app.usecase.portfolio.StockExecutionCsvManagementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app/portfolio/stock-execution")
public class StockExecutionRegistrationApiController {
    private StockExecutionCsvManagementService stockExecutionCsvManagementService;

    public StockExecutionRegistrationApiController(StockExecutionCsvManagementService stockExecutionCsvManagementService){
        this.stockExecutionCsvManagementService = stockExecutionCsvManagementService;
    }

    public
}
