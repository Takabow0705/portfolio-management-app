package project.infra.rdb.strockpricetimeseries;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
@IdClass(StockPriceBase.PK.class)
public class StockPriceBase implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "stock_code")
    private String stockCode;

    @Id
    @Column(name = "base_date")
    private LocalDate baseDate;

    @Column(name = "open_price")
    @NotNull
    private BigDecimal openPrice;

    @Column(name = "high_price")
    @NotNull
    private BigDecimal highPrice;

    @Column(name = "low_price")
    @NotNull
    private BigDecimal lowPrice;

    @Column(name = "close_price")
    @NotNull
    private BigDecimal closePrice;

    @Column(name = "volume")
    @NotNull
    private BigDecimal volume;

    @Column(name = "stock_splits")
    @NotNull
    private boolean stockSplits;

    @Column(name = "is_deleted")
    @NotNull
    private boolean isDeleted;

    @Column(name = "update_timestamp")
    @NotNull
    private Timestamp updateTimestamp;

    @Column(name = "update_user")
    @NotNull
    private String updateUser;

    @Column(name = "create_timestamp")
    @NotNull
    private Timestamp createTimestamp;

    @Column(name = "create_user")
    @NotNull
    private String createUser;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(LocalDate baseDate) {
        this.baseDate = baseDate;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public boolean isStockSplits() {
        return stockSplits;
    }

    public void setStockSplits(boolean stockSplits) {
        this.stockSplits = stockSplits;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "StockPriceBase{" +
                "stockCode='" + stockCode + '\'' +
                ", baseDate=" + baseDate +
                ", openPrice=" + openPrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", closePrice=" + closePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockPriceBase that = (StockPriceBase) o;
        return stockCode.equals(that.stockCode) && baseDate.equals(that.baseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockCode, baseDate);
    }

    @Embeddable
    public static class PK implements Serializable {
        private static final long serialVersionUID = 1l;
        @Column(name = "stock_code")
        private String stockCode;
        @Column(name = "base_date")
        private LocalDate baseDate;

        public String getStockCode() {
            return stockCode;
        }

        public void setStockCode(String stockCode) {
            this.stockCode = stockCode;
        }

        public LocalDate getBaseDate() {
            return baseDate;
        }

        public void setBaseDate(LocalDate baseDate) {
            this.baseDate = baseDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PK pk = (PK) o;
            return stockCode == pk.stockCode && Objects.equals(baseDate, pk.baseDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(stockCode, baseDate);
        }

        @Override
        public String toString() {
            return "PK{" +
                    "stockCode=" + stockCode +
                    ", baseDate=" + baseDate +
                    '}';
        }
    }
}
