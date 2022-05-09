package project.infra.rdb.cashaccount;

import project.infra.rdb.stockportfolio.StockPortfolio;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public class CashAccountBase implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_portfolio_id", referencedColumnName = "id")
    private StockPortfolio stockPortfolio;

    @Column(name = "detail")
    private String detail;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StockPortfolio getStockPortfolio() {
        return stockPortfolio;
    }

    public void setStockPortfolio(StockPortfolio stockPortfolio) {
        this.stockPortfolio = stockPortfolio;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashAccountBase that = (CashAccountBase) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CashBalanceBase{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
