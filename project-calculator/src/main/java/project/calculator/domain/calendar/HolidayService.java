package project.calculator.domain.calendar;

import project.calculator.domain.repository.master.holidays.Holiday;

import java.util.Set;

public interface HolidayService {
    public abstract Set<Holiday> getHolidayByCountryCode(CountryCode countryCode);
}
