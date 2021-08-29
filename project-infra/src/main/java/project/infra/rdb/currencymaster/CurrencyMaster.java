package project.infra.rdb.currencymaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "currency_master")
public class CurrencyMaster implements Serializable {

    private static final Long serialVersionUId = 1L;

    @Id
    @Column(name = "CURRENCY_ID")
    private String currencyId;
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "CURRENCY_NAME")
    private String currencyName;
    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
