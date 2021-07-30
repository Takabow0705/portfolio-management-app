package project.calculator.domain.repository.master.holidays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {

    private static final long serialVersionUID = 1l;
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "country_code")
    private String countryCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holiday holiday = (Holiday) o;
        return id == holiday.id && date.equals(holiday.date) && countryCode.equals(holiday.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, countryCode);
    }
}
