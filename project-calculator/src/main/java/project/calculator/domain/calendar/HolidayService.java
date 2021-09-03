package project.calculator.domain.calendar;

import project.infra.rdb.holiday.Holiday;

import java.util.Set;

public interface HolidayService {
    public abstract Set<Holiday> getHolidayByCountryCode(CountryCode countryCode);
}
