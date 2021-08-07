package project.infra.rdb.stockmaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "stock_master")
public class StockMaster implements Serializable {

    /** serial version UID*/
    private static final Long serialVersionUID = 1L;
    @Id
    @Column(name = "STOCK_CODE")
    private Long stockCode;
    @Column(name = "STOCK_NAME")
    private String stockName;
    @Column(name = "UNIT",nullable = false)
    private BigDecimal unit;
    @Column(name = "MARKET_DIVISION_CODE",nullable = false)
    private Long marketDevisionCode;
    @Column(name = "MARKET")
    private String market;
    @Column(name = "33SECTOR_CODE",nullable = false)
    private Long sectorCode33;
    @Column(name = "33SECTOR_DETAIL")
    private String sectorDetail33;
    @Column(name = "17SECTOR_CODE")
    private Long sectorCode17;
    @Column(name = "17SECTOR_DETAIL")
    private String sectorDetail17;

    public Long getStockCode() {
        return stockCode;
    }

    public void setStockCode(Long stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public Long getMarketDevisionCode() {
        return marketDevisionCode;
    }

    public void setMarketDevisionCode(Long marketDevisionCode) {
        this.marketDevisionCode = marketDevisionCode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Long getSectorCode33() {
        return sectorCode33;
    }

    public void setSectorCode33(Long sectorCode33) {
        this.sectorCode33 = sectorCode33;
    }

    public String getSectorDetail33() {
        return sectorDetail33;
    }

    public void setSectorDetail33(String sectorDetail33) {
        this.sectorDetail33 = sectorDetail33;
    }

    public Long getSectorCode17() {
        return sectorCode17;
    }

    public void setSectorCode17(Long sectorCode17) {
        this.sectorCode17 = sectorCode17;
    }

    public String getSectorDetail17() {
        return sectorDetail17;
    }

    public void setSectorDetail17(String sectorDetail17) {
        this.sectorDetail17 = sectorDetail17;
    }

    @Override
    public String toString() {
        return "StockMaster{" +
                "stockCode=" + stockCode +
                ", stockName='" + stockName + '\'' +
                ", unit=" + unit +
                ", marketDevisionCode=" + marketDevisionCode +
                ", market='" + market + '\'' +
                ", sectorCode33=" + sectorCode33 +
                ", sectorDetail33='" + sectorDetail33 + '\'' +
                ", sectorCode17=" + sectorCode17 +
                ", sectorDetail17='" + sectorDetail17 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockMaster that = (StockMaster) o;
        return stockCode.equals(that.stockCode) &&
                stockName.equals(that.stockName) &&
                Objects.equals(unit, that.unit) &&
                marketDevisionCode.equals(that.marketDevisionCode) &&
                Objects.equals(market, that.market) &&
                sectorCode33.equals(that.sectorCode33) &&
                Objects.equals(sectorDetail33, that.sectorDetail33) &&
                sectorCode17.equals(that.sectorCode17) &&
                Objects.equals(sectorDetail17, that.sectorDetail17);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockCode, stockName, unit, marketDevisionCode, market, sectorCode33, sectorDetail33, sectorCode17, sectorDetail17);
    }
}
