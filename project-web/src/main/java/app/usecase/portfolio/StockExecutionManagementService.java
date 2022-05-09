package app.usecase.portfolio;

import app.domain.auth.UserAuthInfo;
import app.domain.portfolio.CsvUploadResult;
import app.domain.portfolio.StockExectionCsvDto;
import app.domain.portfolio.StockExecutionDownloadParam;

public interface StockExecutionManagementService {
    public abstract String downloadCsv(StockExecutionDownloadParam stockExecutionDownloadParam);
    public abstract CsvUploadResult uploadCsv(StockExectionCsvDto dto, UserAuthInfo session);
}
