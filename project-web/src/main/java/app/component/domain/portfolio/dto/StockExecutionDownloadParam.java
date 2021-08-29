package app.component.domain.portfolio.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class StockExecutionDownloadParam implements Serializable {

    private static final long serialVersionUID = 1l;
    private Long userId;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "StockExecutionDownloadParam{" +
                "userId=" + userId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
