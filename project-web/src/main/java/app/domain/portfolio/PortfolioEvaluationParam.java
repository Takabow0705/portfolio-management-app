package app.domain.portfolio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 評価対象データの絞り込み条件
 */
@Schema(description = "評価対象データの絞り込み条件")
public class PortfolioEvaluationParam {
    @Schema(description = "評価計算対象のポートフォリオID")
    private long portfolioId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @Schema(pattern = "([0-9]{8})", example = "20210401",description = "評価データの抽出開始日")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    @Schema(pattern = "([0-9]{8})", example = "20210409",description = "評価データの抽出終了日")
    private LocalDate endDate;

    public long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortfolioEvaluationParam that = (PortfolioEvaluationParam) o;
        return portfolioId == that.portfolioId && startDate.equals(that.startDate) && endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portfolioId, startDate, endDate);
    }

    @Override
    public String toString() {
        return "PortfolioEvaluationParam{" +
                "portfolioId=" + portfolioId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
