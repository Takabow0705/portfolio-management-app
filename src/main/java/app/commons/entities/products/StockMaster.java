package app.commons.entities.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_master_jp")
public class StockMaster implements Serializable {

    @Id
    @Column(name = "STOCK_CODE")
    private Long stockCode;
    @Column(name = "STOCK_NAME")
    private String stockName;
    @Column(name = "UNIT",nullable = false)
    private BigDecimal unit;
    @Column(name = "MARKET_DVISION_CODE",nullable = false)
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
}
