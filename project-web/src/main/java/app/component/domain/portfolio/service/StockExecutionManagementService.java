package app.component.domain.portfolio.service;

import app.commons.entities.user.UserAuthInfo;
import app.commons.file.csv.StockExecutionRegistrationCsv;
import app.component.domain.portfolio.dto.CsvUploadResult;
import app.component.domain.portfolio.dto.StockExecutionDownloadParam;

import java.util.List;

public interface StockExecutionManagementService {
    public abstract String downloadCsv(StockExecutionDownloadParam stockExecutionDownloadParam);
    public abstract CsvUploadResult uploadCsv(List<StockExecutionRegistrationCsv> csv, UserAuthInfo session);
}
