package app.domain.portfolio;

import java.io.Serializable;
import java.time.LocalDate;

public class StockExecutionSearchParam implements Serializable {

    private static final long serialVersionUID = 1l;
    private Long userId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int size;
    private int page;

    public StockExecutionSearchParam(){}

    public StockExecutionSearchParam(LocalDate startDate, LocalDate endDate, long userId){
        this.userId = userId;
        this.fromDate = startDate;
        this.toDate = endDate;
    }

    public StockExecutionSearchParam(LocalDate fromDate, LocalDate toDate, Long userId, int size, int page) {
        this.userId = userId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.size = size;
        this.page = page;
    }

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "StockExecutionSearchParam{" +
                "userId=" + userId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", size=" + size +
                ", page=" + page +
                '}';
    }
}
