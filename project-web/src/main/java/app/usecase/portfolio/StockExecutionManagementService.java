package app.usecase.portfolio;

import app.domain.auth.UserAuthInfo;
import app.domain.execution.StockExecutionInquiryDto;
import app.domain.portfolio.CsvUploadResult;
import app.domain.portfolio.StockExectionCsvDto;
import app.domain.portfolio.StockExecutionSearchParam;
import org.springframework.data.domain.Page;

public interface StockExecutionManagementService {
    public abstract String downloadCsv(StockExecutionSearchParam stockExecutionSearchParam);
    public abstract CsvUploadResult uploadCsv(StockExectionCsvDto dto, UserAuthInfo session);
    public abstract Page<StockExecutionInquiryDto> search(StockExecutionSearchParam searchParam);
}
