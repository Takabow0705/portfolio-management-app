package app.usecase.portfolio;

import app.domain.auth.UserAuthInfo;
import app.domain.execution.StockExecutionRegistrationCsv;
import app.domain.portfolio.CsvUploadResult;
import app.domain.portfolio.StockExecutionDownloadParam;

import java.util.List;

public interface StockExecutionManagementService {
    public abstract String downloadCsv(StockExecutionDownloadParam stockExecutionDownloadParam);
    public abstract CsvUploadResult uploadCsv(List<StockExecutionRegistrationCsv> csv, UserAuthInfo session);
}
